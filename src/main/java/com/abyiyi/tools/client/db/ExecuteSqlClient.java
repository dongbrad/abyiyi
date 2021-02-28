package com.abyiyi.tools.client.db;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.PersistenceExceptionTranslator;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Created by dongqingsong on 2021/2/28.
 */
public class ExecuteSqlClient extends SqlSessionTemplate {
    public static final Logger logger = LoggerFactory.getLogger(ExecuteSqlClient.class);



    ExecuteSqlClient(SqlSessionFactory sqlSessionFactory){
        super(sqlSessionFactory);
    }

    /**
     * Constructs a Spring managed SqlSession with the {@code SqlSessionFactory}
     * provided as an argument and the given {@code ExecutorType}
     * {@code ExecutorType} cannot be changed once the {@code SqlSessionTemplate}
     * is constructed.
     *
     * @param sqlSessionFactory
     * @param executorType
     */
    public ExecuteSqlClient(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
        super(sqlSessionFactory, executorType);
    }

    /**
     * Constructs a Spring managed {@code SqlSession} with the given
     * {@code SqlSessionFactory} and {@code ExecutorType}.
     * A custom {@code SQLExceptionTranslator} can be provided as an
     * argument so any {@code PersistenceException} thrown by MyBatis
     * can be custom translated to a {@code RuntimeException}
     * The {@code SQLExceptionTranslator} can also be null and thus no
     * exception translation will be done and MyBatis exceptions will be
     * thrown
     *
     * @param sqlSessionFactory
     * @param executorType
     * @param exceptionTranslator
     */
    public ExecuteSqlClient(SqlSessionFactory sqlSessionFactory, ExecutorType executorType, PersistenceExceptionTranslator exceptionTranslator) {
        super(sqlSessionFactory, executorType, exceptionTranslator);
    }

    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return super.getSqlSessionFactory();
    }

    @Override
    public ExecutorType getExecutorType() {
        return super.getExecutorType();
    }

    @Override
    public PersistenceExceptionTranslator getPersistenceExceptionTranslator() {
        return super.getPersistenceExceptionTranslator();
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     */
    @Override
    public <T> T selectOne(String statement) {
        return super.selectOne(statement);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     * @param parameter
     */
    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return super.selectOne(statement, parameter);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     * @param mapKey
     */
    @Override
    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        return super.selectMap(statement, mapKey);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     * @param parameter
     * @param mapKey
     */
    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        return super.selectMap(statement, parameter, mapKey);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     * @param parameter
     * @param mapKey
     * @param rowBounds
     */
    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        return super.selectMap(statement, parameter, mapKey, rowBounds);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     */
    @Override
    public <E> List<E> selectList(String statement) {
        return super.selectList(statement);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     * @param parameter
     */
    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        return super.selectList(statement, parameter);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     * @param parameter
     * @param rowBounds
     */
    @Override
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        return super.selectList(statement, parameter, rowBounds);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     * @param handler
     */
    @Override
    public void select(String statement, ResultHandler handler) {
        super.select(statement, handler);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     * @param parameter
     * @param handler
     */
    @Override
    public void select(String statement, Object parameter, ResultHandler handler) {
        super.select(statement, parameter, handler);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     * @param parameter
     * @param rowBounds
     * @param handler
     */
    @Override
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        super.select(statement, parameter, rowBounds, handler);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     */
    @Override
    public int insert(String statement) {
        return super.insert(statement);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     * @param parameter
     */
    @Override
    public int insert(String statement, Object parameter) {
        return super.insert(statement, parameter);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     */
    @Override
    public int update(String statement) {
        return super.update(statement);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     * @param parameter
     */
    @Override
    public int update(String statement, Object parameter) {
        return super.update(statement, parameter);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     */
    @Override
    public int delete(String statement) {
        return super.delete(statement);
    }

    /**
     * {@inheritDoc}
     *
     * @param statement
     * @param parameter
     */
    @Override
    public int delete(String statement, Object parameter) {
        return super.delete(statement, parameter);
    }

    /**
     * {@inheritDoc}
     *
     * @param type
     */
    @Override
    public <T> T getMapper(Class<T> type) {
        return super.getMapper(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commit() {
        super.commit();
    }

    /**
     * {@inheritDoc}
     *
     * @param force
     */
    @Override
    public void commit(boolean force) {
        super.commit(force);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollback() {
        super.rollback();
    }

    /**
     * {@inheritDoc}
     *
     * @param force
     */
    @Override
    public void rollback(boolean force) {
        super.rollback(force);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        super.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearCache() {
        super.clearCache();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Configuration getConfiguration() {
        return super.getConfiguration();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Connection getConnection() {
        return super.getConnection();
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.2
     */
    @Override
    public List<BatchResult> flushStatements() {
        return super.flushStatements();
    }
}
