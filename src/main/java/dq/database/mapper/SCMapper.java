package dq.database.mapper;

import dq.database.entity.SC;

import java.util.List;

public interface SCMapper {
    public void insertSC(SC sc);
    public List<SC> getAllSC();

}
