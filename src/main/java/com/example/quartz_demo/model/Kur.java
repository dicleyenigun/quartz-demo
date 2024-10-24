package com.example.quartz_demo.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "kur")
public class Kur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Para birimi kodu (USD, EUR gibi)
    @Column(name = "doviz_kodu")
    private String dovizKodu;

    // Para biriminin adı (ABD Doları, Euro gibi)
    @Column(name = "doviz_adi")
    private String dovizAdi;

    // Alış fiyatı (Döviz kuru)
    @Column(name = "doviz_alis")
    private Double dovizAlis;

    // Satış fiyatı (Döviz kuru)
    @Column(name = "doviz_satis")
    private Double dovizSatis;

    // Efektif alış fiyatı
    @Column(name = "efektif_alis")
    private Double efektifAlis;

    // Efektif satış fiyatı
    @Column(name = "efektif_satis")
    private Double efektifSatis;

    // Tarih bilgisi
    @Column(name = "tarih")
    private LocalDate tarih;

    // Constructor'lar, getter'lar ve setter'lar

    public Kur() {
    }

    public Kur(String dovizKodu, String dovizAdi, Double dovizAlis, Double dovizSatis, Double efektifAlis, Double efektifSatis, LocalDate tarih) {
        this.dovizKodu = dovizKodu;
        this.dovizAdi = dovizAdi;
        this.dovizAlis = dovizAlis;
        this.dovizSatis = dovizSatis;
        this.efektifAlis = efektifAlis;
        this.efektifSatis = efektifSatis;
        this.tarih = tarih;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDovizKodu() {
        return dovizKodu;
    }

    public void setDovizKodu(String dovizKodu) {
        this.dovizKodu = dovizKodu;
    }

    public String getDovizAdi() {
        return dovizAdi;
    }

    public void setDovizAdi(String dovizAdi) {
        this.dovizAdi = dovizAdi;
    }

    public Double getDovizAlis() {
        return dovizAlis;
    }

    public void setDovizAlis(Double dovizAlis) {
        this.dovizAlis = dovizAlis;
    }

    public Double getDovizSatis() {
        return dovizSatis;
    }

    public void setDovizSatis(Double dovizSatis) {
        this.dovizSatis = dovizSatis;
    }

    public Double getEfektifAlis() {
        return efektifAlis;
    }

    public void setEfektifAlis(Double efektifAlis) {
        this.efektifAlis = efektifAlis;
    }

    public Double getEfektifSatis() {
        return efektifSatis;
    }

    public void setEfektifSatis(Double efektifSatis) {
        this.efektifSatis = efektifSatis;
    }

    public LocalDate getTarih() {
        return tarih;
    }

    public void setTarih(LocalDate tarih) {
        this.tarih = tarih;
    }

    @Override
    public String toString() {
        return "Kur{" +
                "id=" + id +
                ", dovizKodu='" + dovizKodu + '\'' +
                ", dovizAdi='" + dovizAdi + '\'' +
                ", dovizAlis=" + dovizAlis +
                ", dovizSatis=" + dovizSatis +
                ", efektifAlis=" + efektifAlis +
                ", efektifSatis=" + efektifSatis +
                ", tarih=" + tarih +
                '}';
    }
}

