package com.apap.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.JabatanPegawaiService;
import com.apap.tugas1.service.PegawaiService;


@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	private JabatanPegawaiService jabatanPegawaiService;
	
	@RequestMapping("/")
	private String index() {
		return "index";
	}
	
	@RequestMapping(value = "/pegawai/view")
	public String view(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		if (pegawai == null) {
			System.out.println("null");
		}
		else {
			System.out.println(pegawai.getNama());
			System.out.println(pegawai.getNip());
		}
		JabatanPegawaiModel jabatanPegawai = 
		model.addAttribute("pegawai", pegawai);
		return "view-pegawai";
		//return "index";
	}	
}
