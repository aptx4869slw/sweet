package com.song.sweet.repository;

import com.song.sweet.model.LandTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LandTrackRepository extends JpaRepository<LandTrack, String>, JpaSpecificationExecutor<LandTrack> {

    LandTrack findFirstByIp(String ip);

    LandTrack findAllByUser_Id(Integer userId);

    LandTrack findFirstByIpAndSystemAndBrowser(String ip, String system, String browser);

}
