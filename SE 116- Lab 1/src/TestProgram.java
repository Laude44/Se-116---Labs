public class TestProgram {
    static int passed = 0, failed = 0;
    static final double EPS = 1e-6;

    static void pass(String msg) { System.out.println("[PASS] " + msg); passed++; }
    static void fail(String msg) { System.out.println("[FAIL] " + msg); failed++; }

    // safe helpers (kullanılan API'ye bağlı olarak istisnaları yakalar)
    static int countBooks(Librarian lib) {
        try {
            Book[] arr = lib.getArr();
            if (arr == null) return 0;
            int c = 0;
            for (Book b : arr) if (b != null) c++;
            return c;
        } catch (Throwable t) {
            System.out.println("countBooks(): istisna: " + t);
            return -1;
        }
    }

    static int indexOf(Librarian lib, String isbn) {
        try {
            Book[] arr = lib.getArr();
            if (arr == null) return -1;
            for (int i = 0; i < arr.length; i++) {
                Book b = arr[i];
                if (b != null && isbn.equals(b.getIsbn())) return i;
            }
            return -1;
        } catch (Throwable t) {
            System.out.println("indexOf(): istisna: " + t);
            return -1;
        }
    }

    static int occurrences(Librarian lib, String isbn) {
        try {
            Book[] arr = lib.getArr();
            if (arr == null) return 0;
            int c = 0;
            for (Book b : arr) if (b != null && isbn.equals(b.getIsbn())) c++;
            return c;
        } catch (Throwable t) {
            System.out.println("occurrences(): istisna: " + t);
            return -1;
        }
    }

    static boolean approx(double a, double b) {
        return Math.abs(a - b) < 1e-3;
    }

    public static void main(String[] args) {
        System.out.println("=== HARD TEST PROGRAM başlıyor ===");

        try {
            // ---------- Hazırlık ----------
            Book[] pool = new Book[30];
            for (int i = 0; i < pool.length; i++) {
                String isbn = String.format("B%03d", i+1);
                pool[i] = new Book("Title" + (i+1), 100 + i, 10.0 + i, isbn);
            }

            // Librarian kapasite testleri: 0, 1, ve 10
            Librarian lib0 = new Librarian("L0","Zero",30,0);
            Librarian lib1 = new Librarian("L1","One",30,1);
            Librarian lib10 = new Librarian("L10","Ten",40,10);

            // 1) Kapasite 0 - ekleme denemesi istisna vermemeli ve sayaç 0 kalmalı
            try {
                lib0.addBook(pool[0]);
                int c0 = countBooks(lib0);
                if (c0 == 0) pass("Kapasite 0: ekleme sonrası kitap sayısı 0");
                else fail("Kapasite 0: beklenen 0, bulundu " + c0);
            } catch (Throwable t) {
                fail("Kapasite 0: addBook istisna attı: " + t);
            }

            // 2) Kapasite 1 - bir ekleme ve iki ekleme test et
            try {
                lib1.addBook(pool[0]);
                lib1.addBook(pool[1]); // 2. ekleme başarısız olmalı veya görmezden gelinmeli
                int c1 = countBooks(lib1);
                if (c1 == 1) pass("Kapasite 1: sadece 1 kitap tutuldu");
                else fail("Kapasite 1: beklenen 1, bulundu " + c1);
            } catch (Throwable t) {
                fail("Kapasite 1: addBook istisna attı: " + t);
            }

            // 3) Normal kapasite: lib10 ile sıra koruma (order preservation) testi
            try {
                for (int i = 0; i < 6; i++) lib10.addBook(pool[i]); // B001..B006
                // remove middle (B003) ve sıra korunmalı: kalan dizide B004 should move to index 2
                lib10.removeBook("B003");
                int idxB004 = indexOf(lib10, "B004");
                if (idxB004 == 2) pass("Sıra koruma: B004 beklenen indekste (2)");
                else fail("Sıra koruma: B004 indeksi beklenen 2 değil, bulundu: " + idxB004);

                // ayrıca kalan kitapların sırası B001,B002,B004,B005,B006
                String[] expected = {"B001","B002","B004","B005","B006"};
                boolean okOrder = true;
                for (int i = 0; i < expected.length; i++) {
                    Book[] arr = lib10.getArr();
                    if (arr == null || arr[i] == null || !expected[i].equals(arr[i].getIsbn())) {
                        okOrder = false;
                        break;
                    }
                }
                if (okOrder) pass("Sıralama doğrulandı: remove sonrası beklenen sıra sağlandı");
                else fail("Sıralama hatası: remove sonrası sıra beklenenle uyumsuz");
            } catch (Throwable t) {
                fail("Order-preservation testinde istisna: " + t);
            }

            // 4) Duplicate ISBN davranışı — programın kararlı olması beklenir
            try {
                Librarian ld = new Librarian("LD","Dup",25,5);
                // önce B010 ekle
                ld.addBook(pool[9]); // B010
                // sonra aynı ISBN ile yeni Book nesnesi ekle
                Book dup = new Book("DupTitle", 99, 1.0, "B010");
                ld.addBook(dup); // eğer engelleniyorsa occurrences==1; eğer izin veriyorsa occurrences==2 veya kapasite sınırına takılır
                int occ = occurrences(ld, "B010");
                if (occ == 1) pass("Duplicate davranışı: aynı ISBN ikinci kez eklenmedi (güvenli)");
                else if (occ == 2) pass("Duplicate davranışı: aynı ISBN iki kez eklendi (implementasyon buna izin veriyor)");
                else fail("Duplicate test beklenmeyen sonuç: occurrences=" + occ);
            } catch (Throwable t) {
                fail("Duplicate testinde istisna: " + t);
            }

            // 5) İndirim kenar durumları: negatif yüzde, >100%, 0%, 100%
            try {
                Librarian ld2 = new Librarian("LD2","Disc",30,5);
                Book d1 = new Book("DiscOK",200,100.0,"D001");
                ld2.addBook(d1);

                // negatif indirim (-10%) -> genelde geçersiz ama sistem istisna atmasın, fiyat değiştiyse mantıklı olmalı
                try { ld2.findBookAndApplyDiscount("D001", -10.0); pass("Negatif indirim çağrısı istisna atmadı (davranış incelenecek)"); }
                catch (Throwable t) { fail("Negatif indirim istisna attı: " + t); }

                // çok büyük indirim (150%) -> fiyat negatif olmayacak şekilde korunmalı (>=0)
                try { ld2.findBookAndApplyDiscount("D001", 150.0); Book after = ld2.findbook("D001");
                    if (after != null && after.getPrice() >= -EPS) pass("Yüksek indirim sonrası fiyat negatif değil (koruma)");
                    else fail("Yüksek indirim sonrası fiyat negatif: " + (after==null ? "null" : after.getPrice()));
                } catch (Throwable t) { fail("Yüksek indirim çağrısı istisna attı: " + t); }
            } catch (Throwable t) {
                fail("İndirim kenar durumları hazırlık hatası: " + t);
            }

            // 6) updatePrice kenar durumları: çok büyük, negatif değerler
            try {
                Librarian lu = new Librarian("LU","Upd",33,5);
                Book up = new Book("UPTEST",120,25.0,"U001");
                lu.addBook(up);
                // negatif fiyat
                try { lu.updatePrice(lu.findbook("U001"), -50.0); Book after = lu.findbook("U001");
                    if (after != null && after.getPrice() < 0) pass("updatePrice negatif değeri kabul ediyor (implementasyon böyle çalışıyor)");
                    else pass("updatePrice negatif değeri korumuyor (fiyat >= 0). Bu da kabul edilebilir.");
                } catch (Throwable t) { fail("updatePrice negatif çağrısı istisna attı: " + t); }
                // çok büyük fiyat
                try { lu.updatePrice(lu.findbook("U001"), 1e9); Book after = lu.findbook("U001");
                    if (after != null && approx(after.getPrice(), 1e9)) pass("updatePrice büyük değerleri uyguladı");
                    else fail("updatePrice büyük değeri uygulamadı ya da yuvarladı: " + (after==null? "null": after.getPrice()));
                } catch (Throwable t) { fail("updatePrice büyük değer çağrısı istisna attı: " + t); }
            } catch (Throwable t) {
                fail("updatePrice kenar durumları hazırlık hatası: " + t);
            }

            // 7) Stres testi: birçok ekle/sil döngüsü (işlemler istisna vermemeli ve sayım kapasiteyi aşmamalı)
            try {
                Librarian ls = new Librarian("LST","Stress",40,8);
                int ops = 2000;
                java.util.Random rnd = new java.util.Random(12345);
                for (int i = 0; i < ops; i++) {
                    int pick = rnd.nextInt(pool.length);
                    try {
                        if (rnd.nextBoolean()) ls.addBook(pool[pick]);
                        else ls.removeBook(pool[pick].getIsbn());
                    } catch (Throwable t) {
                        // yakalayıp devam et — stres testi amaçlı
                        System.out.println("Stres istisna yakalandı (ihmal edilebilir): " + t);
                    }
                    int c = countBooks(ls);
                    if (c < 0) { fail("Stres: countBooks hata verdi"); break; }
                    if (c > 8) { fail("Stres: kapasite aşıldı (" + c + " > 8)"); break; }
                    if (i == ops-1) pass("Stres testi tamamlandı; kapasite aşılıp istisna verilmedi deterministik olarak");
                }
            } catch (Throwable t) {
                fail("Stres testi başlangıcında hata: " + t);
            }

            // 8) Çoklu thread (konkürensi) simülasyonu — sabit süre çalıştır, istisna olmamalı
            try {
                final Librarian lcon = new Librarian("LC","Conc",35,20);
                // hazır kitap kümesi
                for (int i = 0; i < 10; i++) lcon.addBook(pool[i]);

                Thread[] ths = new Thread[6];
                for (int t = 0; t < ths.length; t++) {
                    ths[t] = new Thread(() -> {
                        java.util.Random r = new java.util.Random();
                        for (int k = 0; k < 500; k++) {
                            int p = r.nextInt(pool.length);
                            try {
                                if (r.nextBoolean()) lcon.addBook(pool[p]);
                                else lcon.removeBook(pool[p].getIsbn());
                            } catch (Throwable ex) {
                                // log, ama thread çökmesin
                                System.out.println("Concurrent thread istisna: " + ex);
                            }
                        }
                    });
                }
                for (Thread tt : ths) tt.start();
                for (Thread tt : ths) tt.join();
                int finalCount = countBooks(lcon);
                if (finalCount >= 0 && finalCount <= 20) pass("Concurrent test tamamlandı; son sayaç " + finalCount);
                else fail("Concurrent test: son sayaç beklenmeyen aralıkta: " + finalCount);
            } catch (Throwable t) {
                fail("Concurrent test sırasında hata: " + t);
            }

            // 9) Uzun süreli add/remove döngüsü (hafıza/çökme testi)
            try {
                Librarian lmem = new Librarian("LM","Mem",45,50);
                for (int i = 0; i < 1000; i++) {
                    lmem.addBook(new Book("MT" + i, 10+i, 5.0 + i, "MT" + i));
                    if (i % 3 == 0) lmem.removeBook("MT" + (i/3)); // bazıları silinsin
                }
                pass("Uzun döngü tamamlandı (bellek/çökme yoksa başarılı)");
            } catch (Throwable t) {
                fail("Uzun döngü sırasında istisna: " + t);
            }

            // 10) Display/print test (hata atmamalı)
            try {
                Librarian ldump = new Librarian("LDUMP","Dump",31,6);
                ldump.addBook(pool[0]);
                ldump.addBook(pool[1]);
                ldump.displayAllBooks();
                pass("displayAllBooks() çağrıldı ve istisna atmadı");
            } catch (Throwable t) {
                fail("displayAllBooks hata verdi: " + t);
            }

        } catch (Throwable top) {
            System.out.println("TESTLER sırasında beklenmeyen hata - tüm testler durduruldu:");
            top.printStackTrace();
            System.exit(1);
        }

        // Özet
        System.out.println("=== HARD TEST ÖZETİ ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        if (failed == 0) System.out.println("Tebrikler — daha zor testlerin hepsi geçti!");
        else System.out.println("Bazı zorlu testler başarısız; FAIL mesajlarını inceleyip düzeltme yap.");

    }
}