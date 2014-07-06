package com.mini103.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mini103.bbs.model.Board;

public class BoardDao {
	private SqlSession sqlSession;
	@Autowired
	private PlatformTransactionManager transactionManager;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int addItem(Board board) {
		return sqlSession.insert("Board.AddItem", board);
	}

	public Board getItem(int id) {
		return (Board) sqlSession.selectOne("Board.GetItem", id);
	}

	public List<Board> getList() {
		return (List<Board>) sqlSession.selectList("Board.GetList");
	}

	public int updateItem(int id, String subject, String detail) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("id", id);
		parameters.put("subject", subject);
		parameters.put("detail", detail);

		return sqlSession.update("Board.UpdateItem", parameters);
	}

	public void increaseCount(int id) {
		sqlSession.update("Board.IncreaseCount", id);
	}

	public int deleteItem(int id) {
		return sqlSession.delete("Board.DeleteItem", id);
	}

	// 애노테이션 방식의 트랜잭션 처리. 여러개의 DB처리에서 오류가 발생한 경우 예외가 발생하면서 자동적으로 롤백이 수행된다.
	@Transactional
	public Board transactionAnnotation(int id) {
		sqlSession.update("Board.IncreaseCount", id);
		Board board = (Board) sqlSession.selectOne("Board.GetItem", id);

		return board;
	}

	// TransactionManager를 통해서 일반적인 형식의 트랜잭션 처리를 수행한다.
	public void transcationProgrammatic(int id) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			sqlSession.update("Board.IncreaseCount", id);
			sqlSession.update("Board.fault", id);
			sqlSession.update("Board.IncreaseCount", id);
		} catch (Exception ex) {
			transactionManager.rollback(status);
			throw ex;
		}

		transactionManager.commit(status);
	}
}
