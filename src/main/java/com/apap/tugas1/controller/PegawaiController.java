package com.apap.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;


@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired JabatanService jabatanService;

	@RequestMapping("/")
	private String index() {
		return "index";
	}

	@RequestMapping(value = "/pegawai")
	public String viewPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		for (JabatanModel jabatan:pegawai.getJabatanList()) {
			System.out.println(jabatan.getNama());
		}
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("gajiLengkap", Math.round(pegawaiService.getGajiLengkapByNip(nip)));
		model.addAttribute("jabatanList", pegawai.getJabatanList());
		
		return "view-pegawai";
		
	}	

	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String addPegawai(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setInstansi(new InstansiModel());
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listProvinsi", provinsiService.getProvinsiList());
		model.addAttribute("listJabatan", jabatanService.findAllJabatan());
		
		return "tambah-pegawai";
	}

	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegwawaiSubmit(@ModelAttribute PegawaiModel pegawai) {
		String nip = "";
		System.out.println("");
		System.out.println("Nama: " + pegawai.getNama());
		System.out.println("Instansi: " + pegawai.getInstansi().getNama());

		System.out.println(pegawai.getInstansi().getId());
		nip += pegawai.getInstansi().getId();

		System.out.println(pegawai.getTanggalLahir());
		String[] tglLahir = pegawai.getTanggalLahir().toString().split("-");
		String tglLahirString = tglLahir[2] + tglLahir[1] + tglLahir[0].substring(2, 4);
		nip += tglLahirString;

		System.out.println(pegawai.getTahunMasuk());
		nip += pegawai.getTahunMasuk();

		int counterSama = 1;
		for (PegawaiModel pegawaiInstansi:pegawai.getInstansi().getPegawaiInstansi()) {
			if (pegawaiInstansi.getTahunMasuk().equals(pegawai.getTahunMasuk()) && pegawaiInstansi.getTanggalLahir().equals(pegawai.getTanggalLahir())) {
				counterSama += 1;
				if (counterSama > 10) {
					break;
				}
			}	
		}
		nip += "0" + counterSama;

		System.out.println("NIP: " + nip);
		for (JabatanModel jabatan:pegawai.getJabatanList()) {
			System.out.println(jabatan.getNama());
		}
		pegawai.setNip(nip);
		pegawaiService.addPegawai(pegawai);
		return "index";
	}

}
