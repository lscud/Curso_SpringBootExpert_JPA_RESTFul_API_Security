package com.lscud.curso.exemploProjeto.domain.repository;

import com.lscud.curso.exemploProjeto.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
//    List<Cliente> findByNomeLike(String nome);

    @Query(value = " select c from Cliente c where c.nome like :nome") //utilizando hql
    List<Cliente> procuraPorNome(@Param("nome") String nome);

    @Query(value = " select * from Cliente c where c.nome like '%:nome%'", nativeQuery = true) //utilizando sql nativo
    List<Cliente> procuraPorNomeSQL(@Param("nome") String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos p where c.id =:id")
    Cliente findClienteFecthPedidos(@Param("id") Integer id);

}
