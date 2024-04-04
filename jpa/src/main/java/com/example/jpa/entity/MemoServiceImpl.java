package com.example.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jpa.dto.MemoDto;
import com.example.jpa.repository.MemoRepository;

import lombok.RequiredArgsConstructor;

@Service // service 클래스 객체 생성
@RequiredArgsConstructor // @NonNull, final이 붙은 멤버변수를 대상으로 생성자 생성
public class MemoServiceImpl {

    private final MemoRepository memoRepository;

    public List<MemoDto> getMemoList() {
        List<Memo> entities = memoRepository.findAll();

        List<MemoDto> list = new ArrayList<>();

        entities.forEach(entity -> {
            list.add(entityToDto(entity));
        });

        return list;

    }

    // entity와 dto의 용도가 다르니 dto에 일단 담기
    private MemoDto entityToDto(Memo entity) {
        MemoDto mDto = MemoDto.builder().mno(entity.getMno()).memoText(entity.getMemoText()).build();

        return mDto;
    }

    private Memo dtoToEntity(MemoDto mDto) {
        Memo entity = Memo.builder().mno(mDto.getMno()).memoText(mDto.getMemoText()).build();

        return entity;
    }

    public MemoDto getMemo(Long mno) {
        Memo entity = memoRepository.findById(mno).get();
        return entityToDto(entity);
    }

    public MemoDto updateMemo(MemoDto mDto) {
        // update 대상 찾기
        Memo entity = memoRepository.findById(mDto.getMno()).get();
        // 변경
        entity.setMemoText(mDto.getMemoText());

        // 변경될 값을 전달
        return entityToDto(memoRepository.save(entity));
    }

    public void deleteMemo(Long mno) {
        // Memo entity = memoRepository.findById(mno).get();
        // memoRepository.delete(entity);

        memoRepository.deleteById(mno);
    }

    public void insertMemo(MemoDto mDto) {
        // dto ==> entity로
        memoRepository.save(dtoToEntity(mDto));
    }

}

// @Service
// @RequiredArgsConstructor // @NonNull, final이 붙은 멤버변수를 대상으로 생성자 생성
// public class MemoServiceImpl {

// @Autowired
// private MemoRepository memoRepository;

// public List<MemoDto> getMemoList() {

// }
// }
