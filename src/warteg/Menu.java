package warteg;
public class Menu {
    String nama;
    double harga;
    String kategori; // "Makanan" atau "Minuman"

    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double hargaBaru) {
        this.harga = hargaBaru;
    }

    public String getKategori() {
        return kategori;
    }

    @Override
    public String toString() {
        return nama + " - Rp" + harga;
    }
}
