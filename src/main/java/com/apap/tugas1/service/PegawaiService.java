package com.apap.tugas1.service;

import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {
	PegawaiModel getPegawaiDetailByNip(String nip);
	//long countEntity();
	//PegawaiModel getPegawaiDetailById(Long id);
	double getGajiLengkapByNip(String nip);
	void addPegawai(PegawaiModel pegawai);
	PegawaiModel findPegawaiTermuda(long idInstansi);
	PegawaiModel findPegawaiTertua(long idInstansi);
}
