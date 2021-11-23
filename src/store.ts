import { defineStore } from 'pinia';

export type Fuel = [number, string, number];

interface State {
	fuels: Fuel[];
	params_convert: number[];
}

export const useStore = defineStore('main', {
	state: (): State => ({
		fuels: [
			[1, 'Producto', 5],
			[2, 'Unidad', 32],
			[3, 'Unidad', 33],
			[4, 'Unidad', 34],
			[5, 'Unidad', 35],
			[6, 'Unidad', 66],
			[7, 'Unidad', 67],
			[8, 'Unidad', 68],
			[9, 'Unidad', 69],
			[10, 'Unidad', 70],
			[11, 'Unidad', 71],
			[12, 'Unidad', 72],
			[13, 'Unidad', 73],
			[14, 'Unidad', 74],
			[15, 'm3s (METROS CUBICOS)', 2],
			[16, '', 2],
			[17, 'm3 (METROS CUBICOS DE GNL)', 3],
			[18, 'PC (PIES CUBICOS DE GNL)', 3],
			[19, 'gal (GALONES DE GNL)', 3],
			[20, 'Kg. (KILOGRAMOS DE GNL)', 3],
			[21, 'TON (TONELADAS DE GNL)', 3],
			[22, 'boe (BARRIL EQUIVALENTE DE PETROLEO)', 4],
			[23, 'Gasolina 95 (gal)', 4],
			[24, 'Diesel (gal)', 4],
			[25, 'Kerosene (gal)', 4],
			[26, 'Propano (gal)', 4],
			[27, 'Butano (gal)', 4],
			[28, 'GLP (gal)', 4],
			[29, 'Residual 6 (gal)', 4],
			[30, 'Residual 500 (gal)', 4],
			[31, 'GLP (Balones 10 kg)', 4],
			[32, 'Energía', 5],
			[33, 'Gas Natural', 5],
			[34, 'GNL', 5],
			[36, 'Btu', 32],
			[37, 'kBtu', 32],
			[38, 'Million Btu', 32],
			[39, 'Joule', 32],
			[40, 'kiloJoules', 32],
			[41, 'MegaJoules', 32],
			[42, 'Kwh', 32],
			[43, 'MWh', 32],
			[44, 'kcal', 32],
			[45, 'cal', 32],
			[46, 'hp.hr', 32],
			[47, 'PCs (PIES CUBICOS)', 33],
			[48, 'KPCs (MILES DE PIES CUBICOS)', 33],
			[49, 'MPCs (MILLONES DE PIES CUBICOS)', 33],
			[50, 'm3s (METROS CUBICOS)', 33],
			[51, 'm3 (METROS CUBICOS DE GNL)', 34],
			[52, 'PC (PIES CUBICOS DE GNL)', 34],
			[53, 'gal (GALONES DE GNL)', 34],
			[54, 'Kg. (KILOGRAMOS DE GNL)', 34],
			[55, 'TON (TONELADAS DE GNL)', 34],
			[56, 'boe (BARRIL EQUIVALENTE DE PETROLEO)', 35],
			[57, 'Gasolina 95 (gal)', 35],
			[58, 'Diesel (gal)', 35],
			[59, 'Kerosene (gal)', 35],
			[60, 'Propano (gal)', 35],
			[61, 'Butano (gal)', 35],
			[62, 'GLP (gal)', 35],
			[63, 'Residual 6 (gal)', 35],
			[64, 'Residual 500 (gal)', 35],
			[65, 'GLP (Balones 10 kg)', 35],
			[66, 'Petroleo', 5],
			[67, 'Gasolina 95', 5],
			[68, 'Diesel', 5],
			[69, 'Kerosene', 5],
			[70, 'Propano', 5],
			[71, 'Butano', 5],
			[72, 'GLP', 5],
			[73, 'Residual 6', 5],
			[74, 'Residual 500', 5],
			[84, 'gal (Galones)', 66],
			[85, 'Litros', 66],
			[86, 'm3 (Metros Cúbicos)', 66],
			[87, 'Kg (Kilogramos)', 66],
			[88, 'Ton (Toneladas)', 66],
			[89, 'Barril', 66],
			[90, 'gal (Galones)', 67],
			[91, 'Litros', 67],
			[92, 'm3 (Metros Cúbicos)', 67],
			[93, 'Kg (Kilogramos)', 67],
			[94, 'Ton (Toneladas)', 67],
			[95, 'Barril', 67],
			[96, 'gal (Galones)', 68],
			[97, 'Litros', 68],
			[98, 'm3 (Metros Cúbicos)', 68],
			[99, 'Kg (Kilogramos)', 68],
			[100, 'Ton (Toneladas)', 68],
			[101, 'Barril', 68],
			[108, 'gal (Galones)', 69],
			[109, 'Litros', 69],
			[110, 'm3 (Metros Cúbicos)', 69],
			[111, 'Kg (Kilogramos)', 69],
			[112, 'Ton (Toneladas)', 69],
			[113, 'Barril', 69],
			[114, 'gal (Galones)', 70],
			[115, 'Litros', 70],
			[116, 'm3 (Metros Cúbicos)', 70],
			[117, 'Kg (Kilogramos)', 70],
			[118, 'Ton (Toneladas)', 70],
			[119, 'Barril', 70],
			[120, 'gal (Galones)', 71],
			[121, 'Litros', 71],
			[122, 'm3 (Metros Cúbicos)', 71],
			[123, 'Kg (Kilogramos)', 71],
			[124, 'Ton (Toneladas)', 71],
			[125, 'Barril', 71],
			[126, 'gal (Galones)', 72],
			[127, 'Litros', 72],
			[128, 'm3 (Metros Cúbicos)', 72],
			[129, 'Kg (Kilogramos)', 72],
			[130, 'Ton (Toneladas)', 72],
			[131, 'Barril', 72],
			[132, 'gal (Galones)', 73],
			[133, 'Litros', 73],
			[134, 'm3 (Metros Cúbicos)', 73],
			[135, 'Kg (Kilogramos)', 73],
			[136, 'Ton (Toneladas)', 73],
			[137, 'Barril', 73],
			[138, 'gal (Galones)', 74],
			[139, 'Litros', 74],
			[140, 'm3 (Metros Cúbicos)', 74],
			[141, 'Kg (Kilogramos)', 74],
			[142, 'Ton (Toneladas)', 74],
			[143, 'Barril', 74],
		],
		params_convert: [
			138095.24, //boe
			1000.0, //Gas Natural
			123456.79, //Gasolina
			131036.0, //Diesel
			127060.0, //Kerosene
			90500.0, //Propano
			97400.0, //Butano
			91190.0, //GLP
			143150.0, //Residual 6
			143421.0, //Residual 500
		],
	}),
	actions: {
		getFuels(): Fuel[] {
			return this.fuels.filter(f => f[2] === 5);
		},
		getUnits(c: number): Fuel[] {
			return this.fuels.filter(f => f[2] === c);
		},
		convertUnits(cant: number, unit_1: Fuel, unit_2: Fuel): number {
			let totalConvertirUnidades = 0.0;

			if (cant) {
				let parametro = parseFloat(cant as any);

				if (unit_1[0] == 36) {
					parametro = parametro;
				}
				if (unit_1[0] == 37) {
					parametro = parametro * 1000;
				}
				if (unit_1[0] == 38) {
					parametro = parametro * 1000000;
				}
				if (unit_1[0] == 39) {
					parametro = parametro / 1055.06;
				}
				if (unit_1[0] == 40) {
					parametro = (parametro / 1055.06) * 1000;
				}
				if (unit_1[0] == 41) {
					parametro = (parametro / 1055.06) * 1000000;
				}
				if (unit_1[0] == 42) {
					parametro = (parametro / 293.071) * 1000000;
				}
				if (unit_1[0] == 43) {
					parametro = (parametro / 293.071) * 1000 * 1000000;
				}
				if (unit_1[0] == 44) {
					parametro = ((parametro * 4.184) / 1055.06) * 1000;
				}
				if (unit_1[0] == 45) {
					parametro = (parametro * 4.184) / 1055.06;
				}
				if (unit_1[0] == 46) {
					parametro = parametro * 2544.5;
				}
				if (unit_1[0] == 47) {
					parametro = parametro * this.params_convert[1];
				}
				if (unit_1[0] == 48) {
					parametro = parametro * this.params_convert[1] * 1000;
				}
				if (unit_1[0] == 49) {
					parametro = parametro * this.params_convert[1] * 1000000;
				}
				if (unit_1[0] == 50) {
					parametro = (parametro * this.params_convert[1]) / Math.pow(12 * 0.0254, 3);
				}
				if (unit_1[0] == 999) {
					parametro = ((parametro * this.params_convert[1]) / Math.pow(12 * 0.0254, 3)) * 1000;
				}
				if (unit_1[0] == 51) {
					parametro = ((parametro * this.params_convert[1]) / Math.pow(12 * 0.0254, 3)) * 600;
				}
				if (unit_1[0] == 52) {
					parametro = (((parametro * this.params_convert[1]) / Math.pow(12 * 0.0254, 3)) * 600) / 35.314;
				}
				if (unit_1[0] == 53) {
					parametro = (((parametro * this.params_convert[1]) / Math.pow(12 * 0.0254, 3)) * 600) / 264.17;
				}
				if (unit_1[0] == 54) {
					parametro = (((parametro / 450) * this.params_convert[1]) / Math.pow(12 * 0.0254, 3)) * 600;
				}
				if (unit_1[0] == 55) {
					parametro = (((parametro / 450) * this.params_convert[1]) / Math.pow(12 * 0.0254, 3)) * 600 * 1000;
				}
				if (unit_1[0] == 56) {
					parametro = parametro * this.params_convert[0] * 42;
				}
				if (unit_1[0] == 57) {
					parametro = parametro * this.params_convert[2];
				}
				if (unit_1[0] == 58) {
					parametro = parametro * this.params_convert[3];
				}
				if (unit_1[0] == 59) {
					parametro = parametro * this.params_convert[4];
				}
				if (unit_1[0] == 60) {
					parametro = parametro * this.params_convert[5];
				}
				if (unit_1[0] == 61) {
					parametro = parametro * this.params_convert[6];
				}
				if (unit_1[0] == 62) {
					parametro = parametro * this.params_convert[7];
				}
				if (unit_1[0] == 63) {
					parametro = parametro * this.params_convert[8];
				}
				if (unit_1[0] == 64) {
					parametro = parametro * this.params_convert[9];
				}
				if (unit_1[0] == 65) {
					parametro = ((parametro * 10) / 514.2) * 264.172 * this.params_convert[7];
				}

				//Petróleo
				if (unit_1[0] == 84) {
					parametro = parametro;
				}
				if (unit_1[0] == 85) {
					parametro = parametro * 0.264172051241558;
				}
				if (unit_1[0] == 86) {
					parametro = parametro * 264;
				}
				if (unit_1[0] == 87) {
					parametro = parametro * 0.301314487632509;
				}
				if (unit_1[0] == 88) {
					parametro = parametro * 301.314487632509;
				}
				if (unit_1[0] == 89) {
					parametro = parametro * 42;
				}

				//Gasolina 95
				if (unit_1[0] == 90) {
					parametro = parametro;
				}
				if (unit_1[0] == 91) {
					parametro = parametro * 0.264172051241558;
				}
				if (unit_1[0] == 92) {
					parametro = parametro * 264;
				}
				if (unit_1[0] == 93) {
					parametro = parametro * 0.388235294117647;
				}
				if (unit_1[0] == 94) {
					parametro = parametro * 388.235294117647;
				}
				if (unit_1[0] == 95) {
					parametro = parametro * 42;
				}

				//Diesel
				if (unit_1[0] == 96) {
					parametro = parametro;
				}
				if (unit_1[0] == 97) {
					parametro = parametro * 0.264172051241558;
				}
				if (unit_1[0] == 98) {
					parametro = parametro * 264;
				}
				if (unit_1[0] == 99) {
					parametro = parametro * 0.317307692307692;
				}
				if (unit_1[0] == 100) {
					parametro = parametro * 317.307692307692;
				}
				if (unit_1[0] == 101) {
					parametro = parametro * 42;
				}

				//Kerosene
				if (unit_1[0] == 108) {
					parametro = parametro;
				}
				if (unit_1[0] == 109) {
					parametro = parametro * 0.264172051241558;
				}
				if (unit_1[0] == 110) {
					parametro = parametro * 264;
				}
				if (unit_1[0] == 111) {
					parametro = parametro * 0.352;
				}
				if (unit_1[0] == 112) {
					parametro = parametro * 352;
				}
				if (unit_1[0] == 113) {
					parametro = parametro * 42;
				}

				//Propano
				if (unit_1[0] == 114) {
					parametro = parametro;
				}
				if (unit_1[0] == 115) {
					parametro = parametro * 0.264172051241558;
				}
				if (unit_1[0] == 116) {
					parametro = parametro * 264;
				}
				if (unit_1[0] == 117) {
					parametro = parametro * 0.517647058823529;
				}
				if (unit_1[0] == 118) {
					parametro = parametro * 517.647058823529;
				}
				if (unit_1[0] == 119) {
					parametro = parametro * 42;
				}

				//Butano
				if (unit_1[0] == 120) {
					parametro = parametro;
				}
				if (unit_1[0] == 121) {
					parametro = parametro * 0.264172051241558;
				}
				if (unit_1[0] == 122) {
					parametro = parametro * 264;
				}
				if (unit_1[0] == 123) {
					parametro = parametro * 0.45360824742268;
				}
				if (unit_1[0] == 124) {
					parametro = parametro * 453.60824742268;
				}
				if (unit_1[0] == 125) {
					parametro = parametro * 42;
				}

				//GLP
				if (unit_1[0] == 126) {
					parametro = parametro;
				}
				if (unit_1[0] == 127) {
					parametro = parametro * 0.264172051241558;
				}
				if (unit_1[0] == 128) {
					parametro = parametro * 264;
				}
				if (unit_1[0] == 129) {
					parametro = parametro * 0.50965250965251;
				}
				if (unit_1[0] == 130) {
					parametro = parametro * 509.65250965251;
				}
				if (unit_1[0] == 131) {
					parametro = parametro * 42;
				}

				//Residual 6
				if (unit_1[0] == 132) {
					parametro = parametro;
				}
				if (unit_1[0] == 133) {
					parametro = parametro * 0.264172051241558;
				}
				if (unit_1[0] == 134) {
					parametro = parametro * 264;
				}
				if (unit_1[0] == 135) {
					parametro = parametro * 0.272164948453608;
				}
				if (unit_1[0] == 136) {
					parametro = parametro * 272.164948453608;
				}
				if (unit_1[0] == 137) {
					parametro = parametro * 42;
				}

				//Residual 500
				if (unit_1[0] == 138) {
					parametro = parametro;
				}
				if (unit_1[0] == 139) {
					parametro = parametro * 0.264172051241558;
				}
				if (unit_1[0] == 140) {
					parametro = parametro * 264;
				}
				if (unit_1[0] == 141) {
					parametro = parametro * 0.269387755102041;
				}
				if (unit_1[0] == 142) {
					parametro = parametro * 269.387755102041;
				}
				if (unit_1[0] == 143) {
					parametro = parametro * 42;
				}

				switch (unit_2[0]) {
					case 36: //Btu
						totalConvertirUnidades = parametro;
						break;
					case 37: //kBtu
						totalConvertirUnidades = parametro / 1000;
						break;
					case 38: //MillionBtu
						totalConvertirUnidades = parametro / 1000 / 1000;
						break;
					case 39: //Joule
						totalConvertirUnidades = parametro * 1055.06;
						break;
					case 40: //KiloJoule
						totalConvertirUnidades = (parametro * 1055.06) / 1000;
						break;
					case 41: //MegaJoule
						totalConvertirUnidades = (parametro * 1055.06) / 1000 / 1000;
						break;
					case 42: //KwH
						totalConvertirUnidades = (parametro * 293.071) / 1000000;
						break;
					case 43: //Mwh
						totalConvertirUnidades = (parametro * 293.071) / 1000000 / 1000;
						break;
					case 44: //Kcal
						totalConvertirUnidades = (parametro / 4.184) * (1055.06 / 1000);
						break;
					case 45: //Cal
						totalConvertirUnidades = (parametro / 4.184) * (1055.06 / 1000) * 1000;
						break;
					case 46: //Hp.Hr
						totalConvertirUnidades = parametro / 2544.5;
						break;
					case 47: //PCs
						totalConvertirUnidades = parametro / this.params_convert[1];
						break;
					case 48: //MPCs
						totalConvertirUnidades = parametro / this.params_convert[1] / 1000;
						break;
					case 49: //MMPCs
						totalConvertirUnidades = parametro / this.params_convert[1] / 1000000;
						break;
					case 50: //m3s
						totalConvertirUnidades = (parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3);
						break;
					case 999: //Mm3s
						totalConvertirUnidades =
							((parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3)) / 600 / 1000;
						break;
					case 51: //m3
						totalConvertirUnidades =
							((parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3)) / 600;
						break;
					case 52: //PC
						totalConvertirUnidades =
							(((parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3)) / 600) * 35.314;
						break;
					case 53: //gal
						totalConvertirUnidades =
							(((parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3)) / 600) * 264.17;
						break;
					case 54: //kg
						totalConvertirUnidades =
							(((parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3)) / 600) * 450;
						break;
					case 55: //TON
						totalConvertirUnidades =
							((((parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3)) / 600) * 450) / 1000;
						break;
					case 56: //boe
						totalConvertirUnidades = parametro / this.params_convert[0] / 42;
						break;
					case 57: //Gasolina 95
						totalConvertirUnidades = parametro / this.params_convert[2];
						break;
					case 58: // Diesel
						totalConvertirUnidades = parametro / this.params_convert[3];
						break;
					case 59: // Kerosene
						totalConvertirUnidades = parametro / this.params_convert[4];
						break;
					case 60: // Propano
						totalConvertirUnidades = parametro / this.params_convert[5];
						break;
					case 61: // Butano
						totalConvertirUnidades = parametro / this.params_convert[6];
						break;
					case 62: // GLP
						totalConvertirUnidades = parametro / this.params_convert[7];
						break;
					case 63: // Residual 6
						totalConvertirUnidades = parametro / this.params_convert[8];
						break;
					case 64: // Residual 500
						totalConvertirUnidades = parametro / this.params_convert[9];
						break;
					case 65: // GLP	Balones
						totalConvertirUnidades = ((parametro / 10) * 514.2) / 264.172 / this.params_convert[7];
						break;

					// Petroleo
					case 84: //Galones
						totalConvertirUnidades = parametro;
						break;
					case 85: //Litros
						if (unit_1[0] == 86) {
							totalConvertirUnidades = (parametro / 264) * 1000;
						} else {
							totalConvertirUnidades = parametro / 0.264172051241558;
						}
						break;
					case 86: // metros cúbicos
						totalConvertirUnidades = parametro / 264;
						break;
					case 87: // Kilogramos
						totalConvertirUnidades = parametro / 0.301314487632509;
						break;
					case 88: //Toneladas
						totalConvertirUnidades = parametro / 301.314487632509;
						break;
					case 89: //Barrilees
						totalConvertirUnidades = parametro / 42;
						break;

					// Gasolina 95
					case 90: //Galones
						totalConvertirUnidades = parametro;
						break;
					case 91: //Litros
						if (unit_1[0] == 92) {
							totalConvertirUnidades = (parametro / 264) * 1000;
						} else {
							totalConvertirUnidades = parametro / 0.264172051241558;
						}
						break;
					case 92: // metros cúbicos
						totalConvertirUnidades = parametro / 264;
						break;
					case 93: // Kilogramos
						totalConvertirUnidades = parametro / 0.388235294117647;
						break;
					case 94: //Toneladas
						totalConvertirUnidades = parametro / 388.235294117647;
						break;
					case 95: //Barrilees
						totalConvertirUnidades = parametro / 42;
						break;

					// Diesel
					case 96: //Galones
						totalConvertirUnidades = parametro;
						break;
					case 97: //Litros
						if (unit_1[0] == 98) {
							totalConvertirUnidades = (parametro / 264) * 1000;
						} else {
							totalConvertirUnidades = parametro / 0.264172051241558;
						}
						break;
					case 98: // metros cúbicos
						totalConvertirUnidades = parametro / 264;
						break;
					case 99: // Kilogramos
						totalConvertirUnidades = parametro / 0.317307692307692;
						break;
					case 100: //Toneladas
						totalConvertirUnidades = parametro / 317.307692307692;
						break;
					case 101: //Barrilees
						totalConvertirUnidades = parametro / 42;
						break;

					// Kerosene
					case 108: //Galones
						totalConvertirUnidades = parametro;
						break;
					case 109: //Litros
						if (unit_1[0] == 110) {
							totalConvertirUnidades = (parametro / 264) * 1000;
						} else {
							totalConvertirUnidades = parametro / 0.264172051241558;
						}
						break;
					case 110: // metros cúbicos
						totalConvertirUnidades = parametro / 264;
						break;
					case 111: // Kilogramos
						totalConvertirUnidades = parametro / 0.352;
						break;
					case 112: //Toneladas
						totalConvertirUnidades = parametro / 352;
						break;
					case 113: //Barrilees
						totalConvertirUnidades = parametro / 42;
						break;

					// Propano
					case 114: //Galones
						totalConvertirUnidades = parametro;
						break;
					case 115: //Litros
						if (unit_1[0] == 116) {
							totalConvertirUnidades = (parametro / 264) * 1000;
						} else {
							totalConvertirUnidades = parametro / 0.264172051241558;
						}
						break;
					case 116: // metros cúbicos
						totalConvertirUnidades = parametro / 264;
						break;
					case 117: // Kilogramos
						totalConvertirUnidades = parametro / 0.517647058823529;
						break;
					case 118: //Toneladas
						totalConvertirUnidades = parametro / 517.647058823529;
						break;
					case 119: //Barrilees
						totalConvertirUnidades = parametro / 42;
						break;

					// Propano
					case 120: //Galones
						totalConvertirUnidades = parametro;
						break;
					case 121: //Litros
						if (unit_1[0] == 122) {
							totalConvertirUnidades = (parametro / 264) * 1000;
						} else {
							totalConvertirUnidades = parametro / 0.264172051241558;
						}
						break;
					case 122: // metros cúbicos
						totalConvertirUnidades = parametro / 264;
						break;
					case 123: // Kilogramos
						totalConvertirUnidades = parametro / 0.45360824742268;
						break;
					case 124: //Toneladas
						totalConvertirUnidades = parametro / 453.60824742268;
						break;
					case 125: //Barrilees
						totalConvertirUnidades = parametro / 42;
						break;

					// GLP
					case 126: //Galones
						totalConvertirUnidades = parametro;
						break;
					case 127: //Litros
						if (unit_1[0] == 128) {
							totalConvertirUnidades = (parametro / 264) * 1000;
						} else {
							totalConvertirUnidades = parametro / 0.264172051241558;
						}
						break;
					case 128: // metros cúbicos
						totalConvertirUnidades = parametro / 264;
						break;
					case 129: // Kilogramos
						totalConvertirUnidades = parametro / 0.50965250965251;
						break;
					case 130: //Toneladas
						totalConvertirUnidades = parametro / 509.65250965251;
						break;
					case 131: //Barrilees
						totalConvertirUnidades = parametro / 42;
						break;

					// Residual 6
					case 132: //Galones
						totalConvertirUnidades = parametro;
						break;
					case 133: //Litros
						if (unit_1[0] == 134) {
							totalConvertirUnidades = (parametro / 264) * 1000;
						} else {
							totalConvertirUnidades = parametro / 0.264172051241558;
						}
						break;
					case 134: // metros cúbicos
						totalConvertirUnidades = parametro / 264;
						break;
					case 135: // Kilogramos
						totalConvertirUnidades = parametro / 0.272164948453608;
						break;
					case 136: //Toneladas
						totalConvertirUnidades = parametro / 272.164948453608;
						break;
					case 137: //Barrilees
						totalConvertirUnidades = parametro / 42;
						break;

					// Residual 500
					case 138: //Galones
						totalConvertirUnidades = parametro;
						break;
					case 139: //Litros
						if (unit_1[0] == 140) {
							totalConvertirUnidades = (parametro / 264) * 1000;
						} else {
							totalConvertirUnidades = parametro / 0.264172051241558;
						}
						break;
					case 140: // metros cúbicos
						totalConvertirUnidades = parametro / 264;
						break;
					case 141: // Kilogramos
						totalConvertirUnidades = parametro / 0.269387755102041;
						break;
					case 142: //Toneladas
						totalConvertirUnidades = parametro / 269.387755102041;
						break;
					case 143: //Barrilees
						totalConvertirUnidades = parametro / 42;
						break;
				}
			}

			return parseFloat(totalConvertirUnidades.toFixed(2));
		},
		convertEnergy(cant: number, unit_1: Fuel, unit_2: Fuel): [number, string] {
			let totalConvertirEquivalentes = 0.0;
			let simbolo = '';

			if (cant) {
				let parametro = parseFloat(cant as any);

				if (unit_1[0] == 36) {
					parametro = parametro;
				}
				if (unit_1[0] == 37) {
					parametro = parametro * 1000;
				}
				if (unit_1[0] == 38) {
					parametro = parametro * 1000000;
				}
				if (unit_1[0] == 39) {
					parametro = parametro / 1055.06;
				}
				if (unit_1[0] == 40) {
					parametro = (parametro / 1055.06) * 1000;
				}
				if (unit_1[0] == 41) {
					parametro = (parametro / 1055.06) * 1000000;
				}
				if (unit_1[0] == 42) {
					parametro = (parametro / 293.071) * 1000000;
				}
				if (unit_1[0] == 43) {
					parametro = (parametro / 293.071) * 1000 * 1000000;
				}
				if (unit_1[0] == 44) {
					parametro = ((parametro * 4.184) / 1055.06) * 1000;
				}
				if (unit_1[0] == 45) {
					parametro = (parametro * 4.184) / 1055.06;
				}
				if (unit_1[0] == 46) {
					parametro = parametro * 2544.5;
				}
				if (unit_1[0] == 47) {
					parametro = parametro * this.params_convert[1];
				}
				if (unit_1[0] == 48) {
					parametro = parametro * this.params_convert[1] * 1000;
				}
				if (unit_1[0] == 49) {
					parametro = parametro * this.params_convert[1] * 1000000;
				}
				if (unit_1[0] == 50) {
					parametro = (parametro * this.params_convert[1]) / Math.pow(12 * 0.0254, 3);
				}
				if (unit_1[0] == 999) {
					parametro = ((parametro * this.params_convert[1]) / Math.pow(12 * 0.0254, 3)) * 1000;
				}
				if (unit_1[0] == 51) {
					parametro = ((parametro * this.params_convert[1]) / Math.pow(12 * 0.0254, 3)) * 600;
				}
				if (unit_1[0] == 52) {
					parametro = (((parametro * this.params_convert[1]) / Math.pow(12 * 0.0254, 3)) * 600) / 35.314;
				}
				if (unit_1[0] == 53) {
					parametro = (((parametro * this.params_convert[1]) / Math.pow(12 * 0.0254, 3)) * 600) / 264.17;
				}
				if (unit_1[0] == 54) {
					parametro = (((parametro / 450) * this.params_convert[1]) / Math.pow(12 * 0.0254, 3)) * 600;
				}
				if (unit_1[0] == 55) {
					parametro = (((parametro / 450) * this.params_convert[1]) / Math.pow(12 * 0.0254, 3)) * 600 * 1000;
				}
				if (unit_1[0] == 56) {
					parametro = parametro * this.params_convert[0] * 42;
				}
				if (unit_1[0] == 57) {
					parametro = parametro * this.params_convert[2];
				}
				if (unit_1[0] == 58) {
					parametro = parametro * this.params_convert[3];
				}
				if (unit_1[0] == 59) {
					parametro = parametro * this.params_convert[4];
				}
				if (unit_1[0] == 60) {
					parametro = parametro * this.params_convert[5];
				}
				if (unit_1[0] == 61) {
					parametro = parametro * this.params_convert[6];
				}
				if (unit_1[0] == 62) {
					parametro = parametro * this.params_convert[7];
				}
				if (unit_1[0] == 63) {
					parametro = parametro * this.params_convert[8];
				}
				if (unit_1[0] == 64) {
					parametro = parametro * this.params_convert[9];
				}
				if (unit_1[0] == 65) {
					parametro = ((parametro * 10) / 514.2) * 264.172 * this.params_convert[7];
				}

				//Petróleo
				if (unit_1[0] == 84) {
					parametro = parametro * this.params_convert[0];
				}
				if (unit_1[0] == 85) {
					parametro = parametro * this.params_convert[0] * 0.264172051241558;
				}
				if (unit_1[0] == 86) {
					parametro = parametro * this.params_convert[0] * 264;
				}
				if (unit_1[0] == 87) {
					parametro = parametro * this.params_convert[0] * 0.301314487632509;
				}
				if (unit_1[0] == 88) {
					parametro = parametro * this.params_convert[0] * 301.314487632509;
				}
				if (unit_1[0] == 89) {
					parametro = parametro * this.params_convert[0] * 42;
				}

				//Gasolina 95
				if (unit_1[0] == 90) {
					parametro = parametro * this.params_convert[2];
				}
				if (unit_1[0] == 91) {
					parametro = parametro * this.params_convert[2] * 0.264172051241558;
				}
				if (unit_1[0] == 92) {
					parametro = parametro * this.params_convert[2] * 264;
				}
				if (unit_1[0] == 93) {
					parametro = parametro * this.params_convert[2] * 0.388235294117647;
				}
				if (unit_1[0] == 94) {
					parametro = parametro * this.params_convert[2] * 388.235294117647;
				}
				if (unit_1[0] == 95) {
					parametro = parametro * this.params_convert[2] * 42;
				}

				//Diesel
				if (unit_1[0] == 96) {
					parametro = parametro * this.params_convert[3];
				}
				if (unit_1[0] == 97) {
					parametro = parametro * this.params_convert[3] * 0.264172051241558;
				}
				if (unit_1[0] == 98) {
					parametro = parametro * this.params_convert[3] * 264;
				}
				if (unit_1[0] == 99) {
					parametro = parametro * this.params_convert[3] * 0.317307692307692;
				}
				if (unit_1[0] == 100) {
					parametro = parametro * this.params_convert[3] * 317.307692307692;
				}
				if (unit_1[0] == 101) {
					parametro = parametro * this.params_convert[3] * 42;
				}

				//Kerosene
				if (unit_1[0] == 108) {
					parametro = parametro * this.params_convert[4];
				}
				if (unit_1[0] == 109) {
					parametro = parametro * this.params_convert[4] * 0.264172051241558;
				}
				if (unit_1[0] == 110) {
					parametro = parametro * this.params_convert[4] * 264;
				}
				if (unit_1[0] == 111) {
					parametro = parametro * this.params_convert[4] * 0.352;
				}
				if (unit_1[0] == 112) {
					parametro = parametro * this.params_convert[4] * 352;
				}
				if (unit_1[0] == 113) {
					parametro = parametro * this.params_convert[4] * 42;
				}

				//Propano
				if (unit_1[0] == 114) {
					parametro = parametro * this.params_convert[5];
				}
				if (unit_1[0] == 115) {
					parametro = parametro * this.params_convert[5] * 0.264172051241558;
				}
				if (unit_1[0] == 116) {
					parametro = parametro * this.params_convert[5] * 264;
				}
				if (unit_1[0] == 117) {
					parametro = parametro * this.params_convert[5] * 0.517647058823529;
				}
				if (unit_1[0] == 118) {
					parametro = parametro * this.params_convert[5] * 517.647058823529;
				}
				if (unit_1[0] == 119) {
					parametro = parametro * this.params_convert[5] * 42;
				}

				//Butano
				if (unit_1[0] == 120) {
					parametro = parametro * this.params_convert[6];
				}
				if (unit_1[0] == 121) {
					parametro = parametro * this.params_convert[6] * 0.264172051241558;
				}
				if (unit_1[0] == 122) {
					parametro = parametro * this.params_convert[6] * 264;
				}
				if (unit_1[0] == 123) {
					parametro = parametro * this.params_convert[6] * 0.45360824742268;
				}
				if (unit_1[0] == 124) {
					parametro = parametro * this.params_convert[6] * 453.60824742268;
				}
				if (unit_1[0] == 125) {
					parametro = parametro * this.params_convert[6] * 42;
				}

				//GLP
				if (unit_1[0] == 126) {
					parametro = parametro * this.params_convert[7];
				}
				if (unit_1[0] == 127) {
					parametro = parametro * this.params_convert[7] * 0.264172051241558;
				}
				if (unit_1[0] == 128) {
					parametro = parametro * this.params_convert[7] * 264;
				}
				if (unit_1[0] == 129) {
					parametro = (parametro / 514.2) * 264.172 * this.params_convert[7];
				}
				if (unit_1[0] == 130) {
					parametro = parametro * this.params_convert[7] * 509.65250965251;
				}
				if (unit_1[0] == 131) {
					parametro = parametro * this.params_convert[7] * 42;
				}

				//Residual 6
				if (unit_1[0] == 132) {
					parametro = parametro * this.params_convert[8];
				}
				if (unit_1[0] == 133) {
					parametro = parametro * this.params_convert[8] * 0.264172051241558;
				}
				if (unit_1[0] == 134) {
					parametro = parametro * this.params_convert[8] * 264;
				}
				if (unit_1[0] == 135) {
					parametro = parametro * this.params_convert[8] * 0.272164948453608;
				}
				if (unit_1[0] == 136) {
					parametro = parametro * this.params_convert[8] * 272.164948453608;
				}
				if (unit_1[0] == 137) {
					parametro = parametro * this.params_convert[8] * 42;
				}

				//Residual 500
				if (unit_1[0] == 138) {
					parametro = parametro * this.params_convert[9];
				}
				if (unit_1[0] == 139) {
					parametro = parametro * this.params_convert[9] * 0.264172051241558;
				}
				if (unit_1[0] == 140) {
					parametro = parametro * this.params_convert[9] * 264;
				}
				if (unit_1[0] == 141) {
					parametro = parametro * this.params_convert[9] * 0.269387755102041;
				}
				if (unit_1[0] == 142) {
					parametro = parametro * this.params_convert[9] * 269.387755102041;
				}
				if (unit_1[0] == 143) {
					parametro = parametro * this.params_convert[9] * 42;
				}

				switch (unit_2[0]) {
					case 36: //Btu
						totalConvertirEquivalentes = parametro;
						simbolo = 'Btu equiv. de energía eléctrica';
						break;
					case 37: //kBtu
						totalConvertirEquivalentes = parametro / 1000;
						simbolo = 'kBtu equiv. de energía eléctrica';
						break;
					case 38: //MillionBtu
						totalConvertirEquivalentes = parametro / 1000 / 1000;
						simbolo = 'MBtu equiv. de energía eléctrica';
						break;
					case 39: //Joule
						totalConvertirEquivalentes = parametro * 1055.06;
						simbolo = 'J equiv. de energía eléctrica';
						break;
					case 40: //KiloJoule
						totalConvertirEquivalentes = (parametro * 1055.06) / 1000;
						simbolo = 'kJ equiv. de energía eléctrica';
						break;
					case 41: //MegaJoule
						totalConvertirEquivalentes = (parametro * 1055.06) / 1000 / 1000;
						simbolo = 'MJ equiv. de energía eléctrica';
						break;
					case 42: //KwH
						totalConvertirEquivalentes = (parametro * 293.071) / 1000000;
						simbolo = 'KwH equiv. de energía eléctrica';
						break;
					case 43: //Mwh
						totalConvertirEquivalentes = (parametro * 293.071) / 1000000 / 1000;
						simbolo = 'Mwh equiv. de energía eléctrica';
						break;
					case 44: //Kcal
						totalConvertirEquivalentes = (parametro / 4.184) * (1055.06 / 1000);
						simbolo = 'Kcal equiv. de energía eléctrica';
						break;
					case 45: //Cal
						totalConvertirEquivalentes = (parametro / 4.184) * (1055.06 / 1000) * 1000;
						simbolo = 'Cal equiv. de energía eléctrica';
						break;
					case 46: //Hp.Hr
						totalConvertirEquivalentes = parametro / 2544.5;
						simbolo = 'Hp.Hr equiv. de energía eléctrica';
						break;
					case 47: //PCs
						totalConvertirEquivalentes = parametro / this.params_convert[1];
						simbolo = 'PCs equivalentes de GN.';
						break;
					case 48: //MPCs
						totalConvertirEquivalentes = parametro / this.params_convert[1] / 1000;
						simbolo = 'MPCs equivalentes de GN.';
						break;
					case 49: //MMPCs
						totalConvertirEquivalentes = parametro / this.params_convert[1] / 1000000;
						simbolo = 'MMPCs equivalentes de GN.';
						break;
					case 50: //m3s
						totalConvertirEquivalentes = (parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3);
						simbolo = 'm3s equivalentes de GN.';
						break;
					case 999: //Mm3s
						totalConvertirEquivalentes =
							((parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3)) / 600 / 1000;
						simbolo = 'Mm3s equivalentes de GNL.';
						break;
					case 51: //m3
						totalConvertirEquivalentes = ((parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3)) / 600;
						simbolo = 'm3 equivalentes de GNL.';
						break;
					case 52: //PC
						totalConvertirEquivalentes =
							(((parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3)) / 600) * 35.314;
						simbolo = 'PC equivalentes de GNL.';
						break;
					case 53: //gal
						totalConvertirEquivalentes =
							(((parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3)) / 600) * 264.17;
						simbolo = 'gal. equivalentes de GNL.';
						break;
					case 54: //kg
						totalConvertirEquivalentes =
							(((parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3)) / 600) * 450;
						simbolo = 'kg. equivalentes de GNL.';
						break;
					case 55: //TON
						totalConvertirEquivalentes =
							((((parametro / this.params_convert[1]) * Math.pow(12 * 0.0254, 3)) / 600) * 450) / 1000;
						simbolo = 'Ton. equivalentes de GNL.';
						break;
					case 56: //boe
						totalConvertirEquivalentes = parametro / this.params_convert[0] / 42;
						simbolo = 'boe';
						break;
					case 57: //Gasolina 95
						totalConvertirEquivalentes = parametro / this.params_convert[2];
						simbolo = 'Gasolina 95';
						break;
					case 58: // Diesel
						totalConvertirEquivalentes = parametro / this.params_convert[3];
						simbolo = 'Diesel';
						break;
					case 59: // Kerosene
						totalConvertirEquivalentes = parametro / this.params_convert[4];
						simbolo = 'Kerosene';
						break;
					case 60: // Propano
						totalConvertirEquivalentes = parametro / this.params_convert[5];
						simbolo = 'Propano';
						break;
					case 61: // Butano
						totalConvertirEquivalentes = parametro / this.params_convert[6];
						simbolo = 'Butano';
						break;
					case 62: // GLP
						totalConvertirEquivalentes = parametro / this.params_convert[7];
						simbolo = 'GLP';
						break;
					case 63: // Residual 6
						totalConvertirEquivalentes = parametro / this.params_convert[8];
						simbolo = 'Residual 6';
						break;
					case 64: // Residual 500
						totalConvertirEquivalentes = parametro / this.params_convert[9];
						simbolo = 'Residual 500';
						break;
					case 65: // GLP	Balones
						totalConvertirEquivalentes = ((parametro / 10) * 514.2) / 264.172 / this.params_convert[7];
						simbolo = 'GLP Balones';
						break;

					// Petroleo
					case 84: //Galones
						totalConvertirEquivalentes = parametro / this.params_convert[0];
						simbolo = 'gal. equivalentes de Petróleo';
						break;
					case 85: //Litros
						if (unit_1[0] == 86) {
							totalConvertirEquivalentes = (parametro / this.params_convert[0] / 264) * 1000;
						} else {
							totalConvertirEquivalentes = parametro / this.params_convert[0] / 0.264172051241558;
						}
						simbolo = 'L. equivalentes de Petróleo';
						break;
					case 86: // metros cúbicos
						totalConvertirEquivalentes = parametro / this.params_convert[0] / 264;
						simbolo = 'm3 equivalentes de Petróleo';
						break;
					case 87: // Kilogramos
						totalConvertirEquivalentes = parametro / this.params_convert[0] / 0.301314487632509;
						simbolo = 'Kg. equivalentes de Petróleo';
						break;
					case 88: //Toneladas
						totalConvertirEquivalentes = parametro / this.params_convert[0] / 301.314487632509;
						simbolo = 'Ton. equivalentes de Petróleo';
						break;
					case 89: //Barrilees
						totalConvertirEquivalentes = parametro / this.params_convert[0] / 42;
						simbolo = 'Bls. equivalentes de Petróleo (BOE)';
						break;

					// Gasolina 95
					case 90: //Galones
						totalConvertirEquivalentes = parametro / this.params_convert[2];
						simbolo = 'gal. equivalentes de Gasolina 95';
						break;
					case 91: //Litros
						if (unit_1[0] == 92) {
							totalConvertirEquivalentes = (parametro / this.params_convert[2] / 264) * 1000;
						} else {
							totalConvertirEquivalentes = parametro / this.params_convert[2] / 0.264172051241558;
						}
						simbolo = 'L. equivalentes de Gasolina 95';
						break;
					case 92: // metros cúbicos
						totalConvertirEquivalentes = parametro / this.params_convert[2] / 264;
						simbolo = 'm3. equivalentes de Gasolina 95';
						break;
					case 93: // Kilogramos
						totalConvertirEquivalentes = parametro / this.params_convert[2] / 0.388235294117647;
						simbolo = 'Kg. equivalentes de Gasolina 95';
						break;
					case 94: //Toneladas
						totalConvertirEquivalentes = parametro / this.params_convert[2] / 388.235294117647;
						simbolo = 'Ton. equivalentes de Gasolina 95';
						break;
					case 95: //Barrilees
						totalConvertirEquivalentes = parametro / this.params_convert[2] / 42;
						simbolo = 'Bls. equivalentes de Gasolina 95';
						break;

					// Diesel
					case 96: //Galones
						totalConvertirEquivalentes = parametro / this.params_convert[3];
						simbolo = 'gal. equivalentes de Diesel';
						break;
					case 97: //Litros
						if (unit_1[0] == 98) {
							totalConvertirEquivalentes = (parametro / this.params_convert[3] / 264) * 1000;
						} else {
							totalConvertirEquivalentes = parametro / this.params_convert[3] / 0.264172051241558;
						}
						simbolo = 'L. equivalentes de Diesel';
						break;
					case 98: // metros cúbicos
						totalConvertirEquivalentes = parametro / this.params_convert[3] / 264;
						simbolo = 'm3 equivalentes de Diesel';
						break;
					case 99: // Kilogramos
						totalConvertirEquivalentes = parametro / this.params_convert[3] / 0.317307692307692;
						simbolo = 'Kg. equivalentes de Diesel';
						break;
					case 100: //Toneladas
						totalConvertirEquivalentes = parametro / this.params_convert[3] / 317.307692307692;
						simbolo = 'Ton. equivalentes de Diesel';
						break;
					case 101: //Barrilees
						totalConvertirEquivalentes = parametro / this.params_convert[3] / 42;
						simbolo = 'Bls. equivalentes de Diesel';
						break;

					// Kerosene
					case 108: //Galones
						totalConvertirEquivalentes = parametro / this.params_convert[4];
						simbolo = 'gal. equivalentes de Kerosene';
						break;
					case 109: //Litros
						if (unit_1[0] == 110) {
							totalConvertirEquivalentes = (parametro / this.params_convert[4] / 264) * 1000;
						} else {
							totalConvertirEquivalentes = parametro / this.params_convert[4] / 0.264172051241558;
						}
						simbolo = 'L. equivalentes de Kerosene';
						break;
					case 110: // metros cúbicos
						totalConvertirEquivalentes = parametro / this.params_convert[4] / 264;
						simbolo = 'm3 equivalentes de Kerosene';
						break;
					case 111: // Kilogramos
						totalConvertirEquivalentes = parametro / this.params_convert[4] / 0.352;
						simbolo = 'Kg. equivalentes de Kerosene';
						break;
					case 112: //Toneladas
						totalConvertirEquivalentes = parametro / this.params_convert[4] / 352;
						simbolo = 'Ton. equivalentes de Kerosene';
						break;
					case 113: //Barrilees
						totalConvertirEquivalentes = parametro / this.params_convert[4] / 42;
						simbolo = 'Bls. equivalentes de Kerosene';
						break;

					// Propano
					case 114: //Galones
						totalConvertirEquivalentes = parametro / this.params_convert[5];
						simbolo = 'gal. equivalentes de Propano';
						break;
					case 115: //Litros
						if (unit_1[0] == 116) {
							totalConvertirEquivalentes = (parametro / this.params_convert[5] / 264) * 1000;
						} else {
							totalConvertirEquivalentes = parametro / this.params_convert[5] / 0.264172051241558;
						}
						simbolo = 'L. equivalentes de Propano';
						break;
					case 116: // metros cúbicos
						totalConvertirEquivalentes = parametro / this.params_convert[5] / 264;
						simbolo = 'm3 equivalentes de Propano';
						break;
					case 117: // Kilogramos
						totalConvertirEquivalentes = parametro / this.params_convert[5] / 0.517647058823529;
						simbolo = 'Kg. equivalentes de Propano';
						break;
					case 118: //Toneladas
						totalConvertirEquivalentes = parametro / this.params_convert[5] / 517.647058823529;
						simbolo = 'Ton. de Propano';
						break;
					case 119: //Barrilees
						totalConvertirEquivalentes = parametro / this.params_convert[5] / 42;
						simbolo = 'Bls. equivalentes de Propano';
						break;

					// Butano
					case 120: //Galones
						totalConvertirEquivalentes = parametro / this.params_convert[6];
						simbolo = 'gal. equivalentes de Butano';
						break;
					case 121: //Litros
						if (unit_1[0] == 122) {
							totalConvertirEquivalentes = (parametro / this.params_convert[6] / 264) * 1000;
						} else {
							totalConvertirEquivalentes = parametro / this.params_convert[6] / 0.264172051241558;
						}
						simbolo = 'L. equivalentes de Butano';
						break;
					case 122: // metros cúbicos
						totalConvertirEquivalentes = parametro / this.params_convert[6] / 264;
						simbolo = 'm3 equivalentes de Butano';
						break;
					case 123: // Kilogramos
						totalConvertirEquivalentes = parametro / this.params_convert[6] / 0.45360824742268;
						simbolo = 'Kg. equivalentes de Butano';
						break;
					case 124: //Toneladas
						totalConvertirEquivalentes = parametro / this.params_convert[6] / 453.60824742268;
						simbolo = 'Ton. equivalentes de Butano';
						break;
					case 125: //Barrilees
						totalConvertirEquivalentes = parametro / this.params_convert[6] / 42;
						simbolo = 'Bls. equivalentes de Butano';
						break;

					// GLP
					case 126: //Galones
						totalConvertirEquivalentes = parametro / this.params_convert[7];
						simbolo = 'gal. equivalentes de GLP';
						break;
					case 127: //Litros
						if (unit_1[0] == 128) {
							totalConvertirEquivalentes = (parametro / this.params_convert[7] / 264) * 1000;
						} else {
							totalConvertirEquivalentes = parametro / this.params_convert[7] / 0.264172051241558;
						}
						simbolo = 'L. equivalentes de GLP';
						break;
					case 128: // metros cúbicos
						totalConvertirEquivalentes = parametro / this.params_convert[7] / 264;
						simbolo = 'm3 equivalentes de GLP';
						break;
					case 129: // Kilogramos
						totalConvertirEquivalentes = (parametro * 514.2) / 264.172 / this.params_convert[7];
						simbolo = 'Kg. equivalentes de GLP';
						break;
					case 130: //Toneladas
						totalConvertirEquivalentes = parametro / this.params_convert[7] / 509.65250965251;
						simbolo = 'Ton. equivalentes de GLP';
						break;
					case 131: //Barrilees
						totalConvertirEquivalentes = parametro / this.params_convert[7] / 42;
						simbolo = 'Bls. equivalentes de GLP';
						break;

					// Residual 6
					case 132: //Galones
						totalConvertirEquivalentes = parametro / this.params_convert[8];
						simbolo = 'gal. equivalentes de Residual 6';
						break;
					case 133: //Litros
						if (unit_1[0] == 134) {
							totalConvertirEquivalentes = (parametro / this.params_convert[8] / 264) * 1000;
						} else {
							totalConvertirEquivalentes = parametro / this.params_convert[8] / 0.264172051241558;
						}
						simbolo = 'L. equivalentes de Residual 6';
						break;
					case 134: // metros cúbicos
						totalConvertirEquivalentes = parametro / this.params_convert[8] / 264;
						simbolo = 'm3 equivalentes de Residual 6';
						break;
					case 135: // Kilogramos
						totalConvertirEquivalentes = parametro / this.params_convert[8] / 0.272164948453608;
						simbolo = 'Kg. equivalentes de Residual 6';
						break;
					case 136: //Toneladas
						totalConvertirEquivalentes = parametro / this.params_convert[8] / 272.164948453608;
						simbolo = 'Ton. equivalentes de Residual 6';
						break;
					case 137: //Barrilees
						totalConvertirEquivalentes = parametro / this.params_convert[8] / 42;
						simbolo = 'Bls. equivalentes de Residual 6';
						break;

					// Residual 500
					case 138: //Galones
						totalConvertirEquivalentes = parametro / this.params_convert[9];
						simbolo = 'gal. equivalentes de Residual 500';
						break;
					case 139: //Litros
						if (unit_1[0] == 140) {
							totalConvertirEquivalentes = (parametro / this.params_convert[9] / 264) * 1000;
						} else {
							totalConvertirEquivalentes = parametro / this.params_convert[9] / 0.264172051241558;
						}
						simbolo = 'L. equivalentes de Residual 500';
						break;
					case 140: // metros cúbicos
						totalConvertirEquivalentes = parametro / this.params_convert[9] / 264;
						simbolo = 'm3 equivalentes de Residual 500';
						break;
					case 141: // Kilogramos
						totalConvertirEquivalentes = parametro / this.params_convert[9] / 0.269387755102041;
						simbolo = 'Kg. equivalentes de Residual 500';
						break;
					case 142: //Toneladas
						totalConvertirEquivalentes = parametro / this.params_convert[9] / 269.387755102041;
						simbolo = 'Ton. equivalentes de Residual 500';
						break;
					case 143: //Barrilees
						totalConvertirEquivalentes = parametro / this.params_convert[9] / 42;
						simbolo = 'Bls. equivalentes de Residual 500';
						break;
				}
			}

			return [parseFloat(totalConvertirEquivalentes.toFixed(2)), simbolo];
		},
	},
});
