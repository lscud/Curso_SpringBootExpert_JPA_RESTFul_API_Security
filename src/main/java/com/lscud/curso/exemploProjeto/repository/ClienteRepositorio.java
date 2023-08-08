package com.lscud.curso.exemploProjeto.repository;

import com.lscud.curso.exemploProjeto.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNomeLike(String nome);
//    @Autowired
//    private EntityManager entityManager; //Pode remover o EnityManager pois o jparepository ja tem escapsulado um entitymanager

}
