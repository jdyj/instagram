package com.example.instagram.repository;

import com.example.instagram.domain.Board;
import com.example.instagram.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Long save(Board board) {
        em.persist(board);
        return board.getId();
    }

    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findByMember(Member member) {
        return em.createQuery("select b from Board b where b.member = :member", Board.class)
                .setParameter("member" , member)
                .getResultList();
    }

}