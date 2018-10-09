package com.apap.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
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
			System.out.println(pegawai.getId());
			for (JabatanModel jabatan:pegawai.getJabatanList()) {
				System.out.println(jabatan.getNama());
			}
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
}
