package com.lscud.curso.exemploProjeto.repository;

import com.lscud.curso.exemploProjeto.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
//    List<Cliente> findByNomeLike(String nome);

    @Query(value = " select c from Cliente c where c.nome like :nome") //utilizando hql
    List<Cliente> procuraPorNome(@Param("nome") String nome);

    @Query(value = " select * from Cliente c where c.nome like '%:nome%'", nativeQuery = true) //utilizando sql nativo
    List<Cliente> procuraPorNomeSQL(@Param("nome") String nome);
}
