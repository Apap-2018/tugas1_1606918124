package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;


@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired
	private InstansiService instansiService;
	
	@RequestMapping("/")
	private String index() {
		return "index";
	}
	
	@RequestMapping(value = "/pegawai")
	public String viewPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		if (pegawai == null) {
			System.out.println("null");
		}
		else {
			System.out.println(pegawai.getNama());
			System.out.println(pegawai.getNip());
			System.out.println(pegawai.getId());
			System.out.println(pegawai.getInstansi().getNama() + " - " +
							   pegawai.getInstansi().getProvinsi().getNama());
			for (JabatanModel jabatan:pegawai.getJabatanList()) {
				System.out.println(jabatan.getNama());
			}
			System.out.println(pegawaiService.getGajiLengkapByNip(nip));
		}
		//if (jabatanPegawaiService.checkWho(pegawai.getId()) == null) {
			//System.out.println("null");
		//}
		//System.out.println(jabatanPegawaiService.sizeJabatanPegawai());
		//System.out.println(jabatanPegawai.getJabatan().getNama());
		//model.addAttribute("pegawai", pegawai);
		//model.addAttribute("jabatanPegawai", jabatanPegawai);
		//return "view-pegawai";
		return "index";
	}	
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String addPegawai(Model model) {
		model.addAttribute("listProvinsi", provinsiService.getProvinsiList());
		model.addAttribute("pegawai", new PegawaiModel());
		model.addAttribute("instansi", new InstansiModel());
		
		return "tambah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PegawaiModel pegawai) {
		System.out.println(pegawai.getNama());
		return "tambah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah/instansi", method = RequestMethod.GET)
	public @ResponseBody List<InstansiModel> findAllInstansi(@RequestParam(value = "provinsiId", required = true) long provinsiId) {
	    ProvinsiModel provinsi = provinsiService.getProvinsiDetailById(provinsiId);
	    for (InstansiModel instansi:provinsi.getInstansiList()) {
	    	System.out.println(instansi.getNama());
	    }
	    return provinsi.getInstansiList(); 
	}
	
}
