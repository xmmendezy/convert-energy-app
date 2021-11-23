<template>
	<figure class="image is-fullwidth">
		<img src="../assets/our_team.png" />
		<div class="is-overlay">
			<div class="card">
				<div class="card-content">
					<div class="content">
						<p class="card-title mb-0">REDLAND</p>
						<p class="card-title">Energy Project Developers</p>
					</div>
				</div>
			</div>
		</div>
	</figure>
	<div class="content m-5">
		<span class="line mt-3 mb-5"></span>
		<p class="has-text-left">NOSOTROS</p>
		<p class="has-text-justified mt-3">
			Somos una empresa conformada por ingenieros, economistas, profesionales del derecho y regulación energética,
			con amplia experiencia en el extenso campo de la energía, con integridad, solidos valores y un elevado
			compromiso en el desarrollo de cada proyecto en el que participamos.
		</p>
		<p class="has-text-justified mt-3">
			Desarrollamos proyectos de Infraestructura de Gas, petróleo y energía, desde su visualización hasta su
			puesta en marcha, proyectos de Oleoductos, Gasoductos, Redes de gas, Plantas de proceso y almacenamiento de
			líquidos, plantas de GNL, GNC, GLP.
		</p>
		<p class="has-text-left mt-5">Contactenos</p>
		<div class="has-text-left mt-3">
			<fas-envelope class="icon" />
			<span class="is-inline-block ml-3"> contacto@globalredland.com </span>
		</div>
		<div class="has-text-left mt-3">
			<fas-phone class="mb-3" />
			<span class="is-inline-block ml-3">
				Perú : +51 996 685 048 <br />
				Argentina : +54 911 4557 4299
			</span>
		</div>
		<div class="has-text-left mt-3">
			<fas-clock class="icon" />
			<span class="is-inline-block ml-3"> Horario completo </span>
		</div>
		<p class="is-size-7 mt-5">
			<a class="url" @click="openURL">Sitio web Global REDLAND</a>
		</p>
	</div>
</template>

<script setup lang="ts">
import { Browser } from '@capacitor/browser';

const openURL = async () => {
	await Browser.open({ url: 'https://globalredland.com/' });
};
</script>

<style lang="scss" scoped>
@import '../scss/vars.scss';

.card {
	margin: auto;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 70vw;
	background-color: $primary;
	border-radius: 1rem;

	.card-title {
		color: #fff;
	}
}

.line {
	height: 3px;
	width: 30vw;
	display: block;
	background-color: $primary;
}

.url {
	color: $primary;
}
</style>
