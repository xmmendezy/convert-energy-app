import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
	appId: 'com.globalredland.converter.energy',
	appName: 'converter.energy',
	webDir: 'dist',
	bundledWebRuntime: false,
	server: {
		url: 'http://localhost:3000',
		cleartext: true,
	},
};

export default config;
