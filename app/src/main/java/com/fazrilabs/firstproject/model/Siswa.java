package com.fazrilabs.firstproject.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by fazri on 3/20/2016.
 */
@Table(name = "siswa")
public class Siswa extends Model {
    @Column(name = "nama")
    public String nama;

    @Column(name = "hp")
    public long hp;

    @Column(name = "gender")
    public String gender;

    @Column(name = "jenjang")
    public String jenjang;

    @Column(name = "hobi")
    public String hobi;

    @Column(name = "alamat")
    public String alamat;
}
