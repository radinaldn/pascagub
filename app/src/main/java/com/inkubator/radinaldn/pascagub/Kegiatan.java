package com.inkubator.radinaldn.pascagub;

/**
 * Created by radinaldn on 13/03/18.
 */

public class Kegiatan {
    private String foto;
    private String nama_keg;
    private String tanggal_keg;
    private String jenis_keg;
    private String nama_keldesa;

    public Kegiatan (String foto, String nama_keg, String tanggal_keg, String jenis_keg, String nama_keldesa){
        this.foto = foto;
        this.nama_keg = nama_keg;
        this.tanggal_keg = tanggal_keg;
        this.jenis_keg = jenis_keg;
        this.nama_keldesa = nama_keldesa;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNama_keg() {
        return nama_keg;
    }

    public String getNama_keldesa() {
        return nama_keldesa;
    }

    public void setNama_keldesa(String nama_keldesa) {
        this.nama_keldesa = nama_keldesa;
    }

    public void setNama_keg(String nama_keg) {
        this.nama_keg = nama_keg;
    }

    public String getTanggal_keg() {
        return tanggal_keg;
    }

    public void setTanggal_keg(String tanggal_keg) {
        this.tanggal_keg = tanggal_keg;
    }

    public String getJenis_keg() {
        return jenis_keg;
    }

    public void setJenis_keg(String jenis_keg) {
        this.jenis_keg = jenis_keg;
    }
}
