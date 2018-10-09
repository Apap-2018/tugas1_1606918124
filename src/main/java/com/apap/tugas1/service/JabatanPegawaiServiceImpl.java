package com.apap.tugas1.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.JabatanPegawaiDB;

public class JabatanPegawaiServiceImpl implements JabatanPegawaiService {
	@Autowired
	private JabatanPegawaiDB jabatanPegawaiDB;
	
	@Override
	public JabatanModel getJabatanByPegawai(PegawaiModel pegawai) {
		return null;
	}

}
