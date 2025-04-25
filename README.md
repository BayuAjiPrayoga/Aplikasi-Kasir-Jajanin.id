# Mini Proyek Pemrograman Berorientasi Obyek 2

## Informasi Proyek

- **Mata Kuliah:** Pemrograman Berorientasi Obyek 2
- **Dosen Pengampu:** [Muhammad Ikhwan Fathulloh](https://github.com/Muhammad-Ikhwan-Fathulloh)
- **Proyek:** Sistem Manajemen Produk
- **Dibuat oleh:**
  - **Nama:** [Bayu Aji Prayoga](https://github.com/BayuAjiPrayoga)
  - **NPM:** 23552011194
  - **Kelas:** TIF RP-23 CNS A

---

## Judul Studi Kasus

**JAJANIN.ID**

---

## Penjelasan Studi Kasus

Aplikasi ini digunakan untuk mempermudah proses pengelolaan produk dan transaksi dalam sebuah sistem manajemen sederhana. Aplikasi ini memiliki fitur untuk:

- Menambah, melihat, memperbarui, dan menghapus produk
- Melakukan transaksi pembelian produk
- Mencetak struk transaksi
- Login dengan peran admin atau kasir

Aplikasi ini berbasis **Java** dengan antarmuka berbasis teks.

---

## Penjelasan 4 Pilar OOP dalam Studi Kasus

### 1. Inheritance

Inheritance memungkinkan suatu kelas untuk mewarisi atribut dan metode dari kelas lain. Dalam proyek ini, kelas `Admin` dan `Kasir` mewarisi atribut dan metode dari kelas abstrak `User`.

#### Implementasi Inheritance dalam Kode:

```java
public abstract class User {
    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public abstract void showMenu();
}

public class Admin extends User {
    public Admin(int id, String username, String password) {
        super(id, username, password);
    }

    @Override
    public void showMenu() {
        AdminController.menu();
    }
}
---

### 2. Encapsulation
Encapsulation diterapkan dengan baik dalam proyek ini. Atribut di kelas Produk dan Transaksi bersifat private dan hanya dapat diakses melalui metode getter dan setter. Hal ini memastikan bahwa data dalam objek dilindungi dari akses langsung dari luar kelas.
#### Implementasi Encapsulation dalam Kode:
```java
public class Produk {
    private int id;
    private String nama;
    private double harga;
    private int stok;

    public Produk(int id, String nama, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        setHarga(harga);
        setStok(stok);
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }

    public void setHarga(double harga) {
        if (harga < 0) {
            throw new IllegalArgumentException("Harga tidak boleh negatif.");
        }
        this.harga = harga;
    }

    public void setStok(int stok) {
        if (stok < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif.");
        }
        this.stok = stok;
    }
}
````

---

### 3. Polymorphism

Polymorphism diterapkan melalui overriding metode showMenu() di kelas Admin dan Kasir. Setiap kelas memberikan implementasi spesifik untuk metode ini.
#### Implementasi Polymorphism dalam Kode:

```java
public abstract class User {
    public abstract void showMenu();
}

public class Admin extends User {
    @Override
    public void showMenu() {
        AdminController.menu();
    }
}

public class Kasir extends User {
    @Override
    public void showMenu() {
        KasirController.menu(getUsername());
    }
}
```

---

### 4. Abstraction

Abstraction diterapkan melalui penggunaan kelas abstrak User, yang mendefinisikan kontrak untuk kelas turunan (Admin dan Kasir) tanpa mengungkapkan detail implementasinya.
#### Implementasi Abstraction dalam Kode:

```java
public abstract class User {
    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public abstract void showMenu();
}
```

---

## Struktur Tabel Aplikasi

### Tabel `users`

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role ENUM('admin', 'kasir') NOT NULL
);
```

### Tabel `produk`

```sql
CREATE TABLE produk (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    harga DOUBLE NOT NULL,
    stok INT NOT NULL
);
```

### Tabel `transaksi`

```sql
CREATE TABLE transaksi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_produk INT NOT NULL,
    jumlah INT NOT NULL,
    total DOUBLE NOT NULL,
    waktu DATETIME NOT NULL,
    FOREIGN KEY (id_produk) REFERENCES produk(id)
);
```

---

## Tampilan Aplikasi

### Menu Admin

```
=== MENU ADMIN ===
1. Tambah Produk
2. Lihat Produk
3. Hapus Produk
0. Logout
Pilih:
```
### Menu Kasir
```
=== TRANSAKSI ===
1. Lihat Produk
2. Tambah Produk ke Transaksi
3. Cetak Struk
0. Logout
Pilih:

```

### Contoh Output

#### Menambah produk

```
Nama produk: Indomie Goreng
Harga: 3000
Stok: 100
Produk berhasil ditambahkan!
```

#### Melihat Produk

```
==================================================================
| ID  | Nama Produk          | Harga      | Stok  |
==================================================================
| 1   | Indomie Goreng       | Rp3000     | 100   |
==================================================================
```

#### Menghapus Produk

```
Masukkan ID produk yang ingin dihapus: 1
Produk dengan ID 1 berhasil dihapus.
```

#### Struk Belanja

```
==========================================
                 Jajanin.ID               
         Jl. Cikijing No. 1, Majalengka  
==========================================
Kasir     : kasir1                      
Tanggal   : 25-Apr-2025 19:43:44        
------------------------------------------
Nama Barang      Qty      Harga    Subtotal
------------------------------------------
Indomie Grg        90      3000       270000
Air Aqua 600ml    100      4000       400000
Teh Sariwangi       5      5000        25000
Shampoo Sunslk     12     15000       180000
------------------------------------------
                              Total: Rp 875,000
==========================================
       Terima kasih telah berbelanja!     
          Belanja Hemat di Jajanin.ID     
==========================================
```

## Demo Proyek

<ul>
  <li>Github: <a href="">[Github]</a></li>
  <li>Youtube: <a href="">[Youtube]</a></li>
</ul>
