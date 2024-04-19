package com.example.board.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.dto.PageResultDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.MemberRepository;
import com.example.board.repository.ReplyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    // @Override
    // public List<BoardDto> getList() {
    // List<Object[]> result = boardRepository.list();

    // Function<Object[], BoardDto> fn = (entity -> entityToDto((Board) entity[0],
    // (Member) entity[1],
    // (Long) entity[2]));

    // List<BoardDto> list = result.stream().map(fn).collect(Collectors.toList());

    // return list;
    // }

    @Override
    public PageResultDto<BoardDto, Object[]> getList(PageRequestDto requestDto) {

        Page<Object[]> result = boardRepository.list(
                requestDto.getPageable(Sort.by("bno").descending()), requestDto.getType(), requestDto.getKeyword());

        // List<Object[]> result = boardRepository.list(pageable);

        Function<Object[], BoardDto> fn = (entity -> entityToDto((Board) entity[0], (Member) entity[1],
                (Long) entity[2]));

        // List<BoardDto> list = result.stream().map(fn).collect(Collectors.toList());

        return new PageResultDto<>(result, fn);
    }

    @Override
    public BoardDto getRow(Long bno) {
        Object[] objects = boardRepository.getRow(bno);

        return entityToDto((Board) objects[0], (Member) objects[1], (Long) objects[2]);
    }

    @Override
    public Long update(BoardDto dto) {
        Board board = boardRepository.findById(dto.getBno()).get();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());

        boardRepository.save(board);

        return board.getBno();
    }

    @Override
    @Transactional
    public void removeWithReplies(Long bno) {
        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno);
    }

    @Override
    public Long create(BoardDto dto) {
        // dto가 이미 회원이메일을 담고 있기 때문에 새로 작성 시 writer 부분에 기존회원이메일 기입

        Optional<Member> member = memberRepository.findById(dto.getWriterEmail());

        if (member.isPresent()) {
            Board entity = Board.builder().title(dto.getTitle()).content(dto.getContent()).writer(member.get()).build();

            entity = boardRepository.save(entity);

            return entity.getBno();
        }

        return null;

    }

}
