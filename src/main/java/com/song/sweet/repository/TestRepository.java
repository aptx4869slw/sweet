package com.song.sweet.repository;

import com.song.sweet.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TestRepository extends JpaRepository<Test, String>, JpaSpecificationExecutor<Test> {

    Test findFirstByString(String string);

}
