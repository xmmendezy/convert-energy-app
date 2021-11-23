import { existsSync, writeFileSync } from 'fs';

if (existsSync('./ios')) {
	// eslint-disable-next-line @typescript-eslint/no-var-requires
	const config = require('./ios/App/App/capacitor.config.json');

	config.server = {
		url: 'http://localhost:3000',
		cleartext: true,
	};

	writeFileSync('./ios/App/App/capacitor.config.json', JSON.stringify(config), 'utf8');
}
