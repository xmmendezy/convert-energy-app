package com.globalredland.converter.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "combustibles")
public class FuelEntry {

    @PrimaryKey
    private int id;
    private String descripcion;
    private int clase;

    public FuelEntry(int id, String descripcion, int clase) {
        this.id = id;
        this.descripcion = descripcion;
        this.clase = clase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getClase() {
        return clase;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }

    public static FuelEntry[] populateData() {
        return new FuelEntry[]{
                new FuelEntry(1, "Producto", 5),
                new FuelEntry(2, "Unidad", 32),
                new FuelEntry(3, "Unidad", 33),
                new FuelEntry(4, "Unidad", 34),
                new FuelEntry(5, "Unidad", 35),
                new FuelEntry(6, "Unidad", 66),
                new FuelEntry(7, "Unidad", 67),
                new FuelEntry(8, "Unidad", 68),
                new FuelEntry(9, "Unidad", 69),
                new FuelEntry(10, "Unidad", 70),
                new FuelEntry(11, "Unidad", 71),
                new FuelEntry(12, "Unidad", 72),
                new FuelEntry(13, "Unidad", 73),
                new FuelEntry(14, "Unidad", 74),
                new FuelEntry(15, "m3s (METROS CUBICOS)", 2),
                new FuelEntry(16, "", 2),
                new FuelEntry(17, "m3 (METROS CUBICOS DE GNL)", 3),
                new FuelEntry(18, "PC (PIES CUBICOS DE GNL)", 3),
                new FuelEntry(19, "gal (GALONES DE GNL)", 3),
                new FuelEntry(20, "Kg. (KILOGRAMOS DE GNL)", 3),
                new FuelEntry(21, "TON (TONELADAS DE GNL)", 3),
                new FuelEntry(22, "boe (BARRIL EQUIVALENTE DE PETROLEO)", 4),
                new FuelEntry(23, "Gasolina 95 (gal)", 4),
                new FuelEntry(24, "Diesel (gal)", 4),
                new FuelEntry(25, "Kerosene (gal)", 4),
                new FuelEntry(26, "Propano (gal)", 4),
                new FuelEntry(27, "Butano (gal)", 4),
                new FuelEntry(28, "GLP (gal)", 4),
                new FuelEntry(29, "Residual 6 (gal)", 4),
                new FuelEntry(30, "Residual 500 (gal)", 4),
                new FuelEntry(31, "GLP (Balones 10 kg)", 4),
                new FuelEntry(32, "Energía", 5),
                new FuelEntry(33, "Gas Natural", 5),
                new FuelEntry(34, "GNL", 5),
                new FuelEntry(36, "Btu", 32),
                new FuelEntry(37, "kBtu", 32),
                new FuelEntry(38, "Million Btu", 32),
                new FuelEntry(39, "Joule", 32),
                new FuelEntry(40, "kiloJoules", 32),
                new FuelEntry(41, "MegaJoules", 32),
                new FuelEntry(42, "Kwh", 32),
                new FuelEntry(43, "MWh", 32),
                new FuelEntry(44, "kcal", 32),
                new FuelEntry(45, "cal", 32),
                new FuelEntry(46, "hp.hr", 32),
                new FuelEntry(47, "PCs (PIES CUBICOS)", 33),
                new FuelEntry(48, "KPCs (MILES DE PIES CUBICOS)", 33),
                new FuelEntry(49, "MPCs (MILLONES DE PIES CUBICOS)", 33),
                new FuelEntry(50, "m3s (METROS CUBICOS)", 33),
                new FuelEntry(51, "m3 (METROS CUBICOS DE GNL)", 34),
                new FuelEntry(52, "PC (PIES CUBICOS DE GNL)", 34),
                new FuelEntry(53, "gal (GALONES DE GNL)", 34),
                new FuelEntry(54, "Kg. (KILOGRAMOS DE GNL)", 34),
                new FuelEntry(55, "TON (TONELADAS DE GNL)", 34),
                new FuelEntry(56, "boe (BARRIL EQUIVALENTE DE PETROLEO)", 35),
                new FuelEntry(57, "Gasolina 95 (gal)", 35),
                new FuelEntry(58, "Diesel (gal)", 35),
                new FuelEntry(59, "Kerosene (gal)", 35),
                new FuelEntry(60, "Propano (gal)", 35),
                new FuelEntry(61, "Butano (gal)", 35),
                new FuelEntry(62, "GLP (gal)", 35),
                new FuelEntry(63, "Residual 6 (gal)", 35),
                new FuelEntry(64, "Residual 500 (gal)", 35),
                new FuelEntry(65, "GLP (Balones 10 kg)", 35),
                new FuelEntry(66, "Petroleo", 5),
                new FuelEntry(67, "Gasolina 95", 5),
                new FuelEntry(68, "Diesel", 5),
                new FuelEntry(69, "Kerosene", 5),
                new FuelEntry(70, "Propano", 5),
                new FuelEntry(71, "Butano", 5),
                new FuelEntry(72, "GLP", 5),
                new FuelEntry(73, "Residual 6", 5),
                new FuelEntry(74, "Residual 500", 5),
                new FuelEntry(84, "gal (Galones)", 66),
                new FuelEntry(85, "Litros", 66),
                new FuelEntry(86, "m3 (Metros Cúbicos)", 66),
                new FuelEntry(87, "Kg (Kilogramos)", 66),
                new FuelEntry(88, "Ton (Toneladas)", 66),
                new FuelEntry(89, "Barril", 66),
                new FuelEntry(90, "gal (Galones)", 67),
                new FuelEntry(91, "Litros", 67),
                new FuelEntry(92, "m3 (Metros Cúbicos)", 67),
                new FuelEntry(93, "Kg (Kilogramos)", 67),
                new FuelEntry(94, "Ton (Toneladas)", 67),
                new FuelEntry(95, "Barril", 67),
                new FuelEntry(96, "gal (Galones)", 68),
                new FuelEntry(97, "Litros", 68),
                new FuelEntry(98, "m3 (Metros Cúbicos)", 68),
                new FuelEntry(99, "Kg (Kilogramos)", 68),
                new FuelEntry(100, "Ton (Toneladas)", 68),
                new FuelEntry(101, "Barril", 68),
                new FuelEntry(108, "gal (Galones)", 69),
                new FuelEntry(109, "Litros", 69),
                new FuelEntry(110, "m3 (Metros Cúbicos)", 69),
                new FuelEntry(111, "Kg (Kilogramos)", 69),
                new FuelEntry(112, "Ton (Toneladas)", 69),
                new FuelEntry(113, "Barril", 69),
                new FuelEntry(114, "gal (Galones)", 70),
                new FuelEntry(115, "Litros", 70),
                new FuelEntry(116, "m3 (Metros Cúbicos)", 70),
                new FuelEntry(117, "Kg (Kilogramos)", 70),
                new FuelEntry(118, "Ton (Toneladas)", 70),
                new FuelEntry(119, "Barril", 70),
                new FuelEntry(120, "gal (Galones)", 71),
                new FuelEntry(121, "Litros", 71),
                new FuelEntry(122, "m3 (Metros Cúbicos)", 71),
                new FuelEntry(123, "Kg (Kilogramos)", 71),
                new FuelEntry(124, "Ton (Toneladas)", 71),
                new FuelEntry(125, "Barril", 71),
                new FuelEntry(126, "gal (Galones)", 72),
                new FuelEntry(127, "Litros", 72),
                new FuelEntry(128, "m3 (Metros Cúbicos)", 72),
                new FuelEntry(129, "Kg (Kilogramos)", 72),
                new FuelEntry(130, "Ton (Toneladas)", 72),
                new FuelEntry(131, "Barril", 72),
                new FuelEntry(132, "gal (Galones)", 73),
                new FuelEntry(133, "Litros", 73),
                new FuelEntry(134, "m3 (Metros Cúbicos)", 73),
                new FuelEntry(135, "Kg (Kilogramos)", 73),
                new FuelEntry(136, "Ton (Toneladas)", 73),
                new FuelEntry(137, "Barril", 73),
                new FuelEntry(138, "gal (Galones)", 74),
                new FuelEntry(139, "Litros", 74),
                new FuelEntry(140, "m3 (Metros Cúbicos)", 74),
                new FuelEntry(141, "Kg (Kilogramos)", 74),
                new FuelEntry(142, "Ton (Toneladas)", 74),
                new FuelEntry(143, "Barril", 74)
        };
    }
}