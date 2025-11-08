package warteg;
import java.util.Scanner;

public class Main {
    static Menu[] menuRestoran = new Menu[10]; // kapasitas awal
    static int jumlahMenu = 0;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        inisialisasiMenu();
        menuUtama();
    }

    static void inisialisasiMenu() {
        menuRestoran[jumlahMenu++] = new Menu("Nasi Goreng", 25000, "Makanan");
        menuRestoran[jumlahMenu++] = new Menu("Mie Ayam", 20000, "Makanan");
        menuRestoran[jumlahMenu++] = new Menu("Ayam Geprek", 28000, "Makanan");
        menuRestoran[jumlahMenu++] = new Menu("Sate Ayam", 30000, "Makanan");
        menuRestoran[jumlahMenu++] = new Menu("Es Teh", 8000, "Minuman");
        menuRestoran[jumlahMenu++] = new Menu("Jus Alpukat", 15000, "Minuman");
        menuRestoran[jumlahMenu++] = new Menu("Air Mineral", 6000, "Minuman");
        menuRestoran[jumlahMenu++] = new Menu("Kopi Susu", 12000, "Minuman");
    }

    static void menuUtama() {
        int pilihan;
        do {
            System.out.println("\n===== APLIKASI RESTORAN =====");
            System.out.println("1. Menu Pelanggan");
            System.out.println("2. Manajemen Menu (Pemilik)");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    menuPelanggan();
                    break;
                case 2:
                    menuManajemen();
                    break;
                case 3:
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 3);
    }

    static void menuPelanggan() {
        input.nextLine(); // bersihkan buffer
        String[] pesanan = new String[50];
        int[] jumlah = new int[50];
        int indexPesanan = 0;

        while (true) {
            tampilkanMenu();
            System.out.print("\nMasukkan nama menu ('selesai' untuk berhenti): ");
            String namaPesanan = input.nextLine();

            if (namaPesanan.equalsIgnoreCase("selesai")) break;

            int indexMenu = cariMenu(namaPesanan);
            if (indexMenu == -1) {
                System.out.println("Menu tidak ditemukan!");
                continue;
            }

            System.out.print("Jumlah: ");
            jumlah[indexPesanan] = input.nextInt();
            input.nextLine();
            pesanan[indexPesanan] = namaPesanan;
            indexPesanan++;
        }

        cetakStruk(pesanan, jumlah, indexPesanan);
    }

    static void tampilkanMenu() {
        System.out.println("\n--- Daftar Menu ---");
        System.out.println("Makanan:");
        for (int i = 0; i < jumlahMenu; i++) {
            if (menuRestoran[i].getKategori().equalsIgnoreCase("Makanan"))
                System.out.println("- " + menuRestoran[i]);
        }
        System.out.println("Minuman:");
        for (int i = 0; i < jumlahMenu; i++) {
            if (menuRestoran[i].getKategori().equalsIgnoreCase("Minuman"))
                System.out.println("- " + menuRestoran[i]);
        }
    }

    static int cariMenu(String nama) {
        for (int i = 0; i < jumlahMenu; i++) {
            if (menuRestoran[i].getNama().equalsIgnoreCase(nama))
                return i;
        }
        return -1;
    }

    static void cetakStruk(String[] pesanan, int[] jumlah, int banyakPesanan) {
        double total = 0;
        System.out.println("\n===== STRUK PEMBAYARAN =====");
        for (int i = 0; i < banyakPesanan; i++) {
            int idx = cariMenu(pesanan[i]);
            double subtotal = menuRestoran[idx].getHarga() * jumlah[i];
            total += subtotal;
            System.out.println(pesanan[i] + " x" + jumlah[i] + " = Rp" + subtotal);
        }

        double pajak = total * 0.10;
        double service = 20000;
        double diskon = 0;
        boolean promoMinuman = false;

        if (total > 100000) diskon = total * 0.10;
        else if (total > 50000) promoMinuman = true;

        double totalAkhir = total + pajak + service - diskon;

        System.out.println("-----------------------------");
        System.out.println("Subtotal: Rp" + total);
        System.out.println("Pajak (10%): Rp" + pajak);
        System.out.println("Layanan: Rp" + service);
        if (diskon > 0) System.out.println("Diskon 10%: -Rp" + diskon);
        if (promoMinuman) System.out.println("Promo: Beli 1 gratis 1 (minuman)");
        System.out.println("Total Bayar: Rp" + totalAkhir);
    }

    static void menuManajemen() {
        int pilihan;
        do {
            System.out.println("\n===== MANAJEMEN MENU =====");
            System.out.println("1. Lihat Menu");
            System.out.println("2. Tambah Menu");
            System.out.println("3. Ubah Harga");
            System.out.println("4. Hapus Menu");
            System.out.println("5. Kembali");
            System.out.print("Pilih: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1 -> tampilkanMenu();
                case 2 -> tambahMenu();
                case 3 -> ubahHarga();
                case 4 -> hapusMenu();
                case 5 -> System.out.println("Kembali ke menu utama...");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    static void tambahMenu() {
        input.nextLine();
        System.out.print("Nama menu baru: ");
        String nama = input.nextLine();
        System.out.print("Harga: ");
        double harga = input.nextDouble();
        input.nextLine();
        System.out.print("Kategori (Makanan/Minuman): ");
        String kategori = input.nextLine();

        menuRestoran[jumlahMenu++] = new Menu(nama, harga, kategori);
        System.out.println("Menu berhasil ditambahkan!");
    }

    static void ubahHarga() {
        tampilkanMenu();
        input.nextLine();
        System.out.print("\nNama menu yang ingin diubah: ");
        String nama = input.nextLine();
        int idx = cariMenu(nama);
        if (idx == -1) {
            System.out.println("Menu tidak ditemukan!");
            return;
        }
        System.out.print("Harga baru: ");
        double hargaBaru = input.nextDouble();
        input.nextLine();
        System.out.print("Yakin ubah? (Ya/Tidak): ");
        String konfirmasi = input.nextLine();
        if (konfirmasi.equalsIgnoreCase("Ya")) {
            menuRestoran[idx].setHarga(hargaBaru);
            System.out.println("Harga berhasil diubah!");
        } else System.out.println("Batal ubah.");
    }

    static void hapusMenu() {
        tampilkanMenu();
        input.nextLine();
        System.out.print("\nNama menu yang ingin dihapus: ");
        String nama = input.nextLine();
        int idx = cariMenu(nama);
        if (idx == -1) {
            System.out.println("Menu tidak ditemukan!");
            return;
        }
        System.out.print("Yakin hapus? (Ya/Tidak): ");
        String konfirmasi = input.nextLine();
        if (konfirmasi.equalsIgnoreCase("Ya")) {
            for (int i = idx; i < jumlahMenu - 1; i++) {
                menuRestoran[i] = menuRestoran[i + 1];
            }
            jumlahMenu--;
            System.out.println("Menu berhasil dihapus!");
        } else System.out.println("Batal hapus.");
    }
}
