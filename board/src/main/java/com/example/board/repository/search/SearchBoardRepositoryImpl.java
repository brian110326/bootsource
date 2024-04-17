package com.example.board.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.board.entity.Board;
import com.example.board.entity.QBoard;
import com.example.board.entity.QMember;
import com.example.board.entity.QReply;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Page<Object[]> list(Pageable pageable) {
        log.info("Board + Reply + Member Join");

        // Q class 사용
        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        // @Query("select b,m from Board b left join b.writer m")
        JPQLQuery<Board> query = from(board);
        query.leftJoin(board.writer, member);

        // 댓글 개수도 같이 보여주기
        // subquery => JPAExpressions
        JPQLQuery<Long> replyCount = JPAExpressions.select(reply.rno.count().as("replycnt")).from(reply)
                .where(reply.board.eq(board))
                .groupBy(reply.board);

        JPQLQuery<Tuple> tuple = query.select(board, member, replyCount);

        // 페이지 나누기
        // sort 지정
        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder<Board> orderByExpression = new PathBuilder<>(Board.class, "board");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        // 페이지 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        // 전체개수
        long count = tuple.fetchCount();

        List<Object[]> list = result.stream().map(t -> t.toArray()).collect(Collectors.toList());

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Object[] getRow(Long bno) {
        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        // @Query("select b,m from Board b left join b.writer m")
        JPQLQuery<Board> query = from(board);
        query.leftJoin(board.writer, member);
        query.where(board.bno.eq(bno));

        // 댓글 개수도 같이 보여주기
        // subquery => JPAExpressions
        JPQLQuery<Long> replyCount = JPAExpressions.select(reply.rno.count().as("replycnt")).from(reply)
                .where(reply.board.eq(board))
                .groupBy(reply.board);

        JPQLQuery<Tuple> tuple = query.select(board, member, replyCount);

        Tuple result = tuple.fetch().get(0);

        return result.toArray();
    }

}
