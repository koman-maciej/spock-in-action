package com.oracle.trs.service;

import com.oracle.trs.model.TrsModel;
import com.oracle.trs.model.TrsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TrsService {

    private TrsRepository trsRepository;

    @Autowired
    public TrsService(TrsRepository trsRepository) {
        this.trsRepository = trsRepository;
    }

    public TrsModel insertOrUpdate(TrsModel trs) {
        return trsRepository.save(trs);
    }

    public TrsModel find(String title) {
        return trsRepository.findById(title)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<TrsModel> findAll() {
        return StreamSupport.stream(trsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void delete(String title) {
        Optional<TrsModel> schrodingerCatModel = trsRepository.findById(title);

        schrodingerCatModel.ifPresent(trsRepository::delete);
    }
}
