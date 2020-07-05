package com.kcnet.todosv.boards;

import com.kcnet.todosv.lists.Lists;
import com.kcnet.todosv.lists.ListsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardsService {

    private final BoardsRepository boardsRepository;
    private final ListsRepository listsRepository;
    private final ModelMapper modelMapper;

    public BoardsService(BoardsRepository boardsRepository, ListsRepository listsRepository, ModelMapper modelMapper) {
        this.boardsRepository = boardsRepository;
        this.listsRepository = listsRepository;
        this.modelMapper = modelMapper;
    }

    public List<Boards> fetchAll() {
        return this.boardsRepository.findAll();
    }

    public Boards fetchBoard(String boardId) {
        return this.boardsRepository.findById(boardId).get();
    }

    public Boards createBoard(BoardsDto dto) {
        String boardId = generateBoardId();
        dto.setBoardId(boardId);
        Boards board = modelMapper.map(dto, Boards.class);
        this.boardsRepository.save(board);
        this.createDefaultList(boardId);

        return board;
    }

    public Boards modifyBoard(BoardsDto dto) {
        Boards board = modelMapper.map(dto, Boards.class);
        this.boardsRepository.save(board);
        return board;
    }

    public void deleteBoard(String boardId) {
        this.boardsRepository.deleteById(boardId);
    }

    private String generateBoardId() {
        String nextId = "B001";
        Optional<Boards> lastBoardOptional = this.boardsRepository.findFirstByOrderByCreatedAtDesc();
        if(lastBoardOptional.isPresent()) {
            nextId = "B" + String.format("%03d", Integer.parseInt(lastBoardOptional.get().getBoardId().replace("B", "")) + 1);
        }
        return nextId;
    }

    private void createDefaultList(String boardId) {
        int pos = 65535;
        Optional<Lists> lastListOptional = this.listsRepository.findFirstByOrderByCreatedAtDesc();
        int nextSeq = Integer.parseInt(lastListOptional.get().getListId().replace("L", "")) + 1;
        this.listsRepository.save(new Lists(boardId, generateNextId(nextSeq), "Todo", pos));
        this.listsRepository.save( new Lists(boardId, generateNextId(nextSeq + 1), "Doing", pos*2));
        this.listsRepository.save(new Lists(boardId, generateNextId(nextSeq + 2), "Done", pos*4));
    }

    private String generateNextId(int seq) {
        return "L" + String.format("%03d", seq);
    }

}
