package com.apap.tugas1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDB;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	@Autowired
	private PegawaiDB pegawaiDB;

	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDB.findByNip(nip);
	}

	@Override
	public long countEntity() {
		return pegawaiDB.count();
	}

	@Override
	public PegawaiModel getPegawaiDetailById(Long id) {
		return pegawaiDB.getOne(id);
	}
	
}
