<template>
	<nav class="navbar is-fixed-top" ref="menu">
		<div class="navbar-brand">
			<a class="navbar-burger" @click="toggleActive">
				<span aria-hidden="true"></span>
				<span aria-hidden="true"></span>
				<span aria-hidden="true"></span>
			</a>

			<p class="navbar-item navbar-title has-text-weight-bold">REDLAND</p>
		</div>

		<div class="navbar-menu" :class="[{ 'is-active': active, 'menu-transition': transition }]">
			<div class="navbar-start">
				<div class="card is-shadowless mb-5">
					<div class="card-content">
						<div class="content">
							<p class="has-text-left has-text-weight-bold is-size-5 mb-1">Global RedLand</p>
							<p class="has-text-left is-size-6">contancto@globalredland.com</p>
						</div>
					</div>
				</div>

				<router-link class="navbar-item has-text-left ml-4 mb-2" to="/" @click="toggleActive">
					<fas-home class="icon" />
					<span class="ml-4"> Inicio </span>
				</router-link>

				<router-link class="navbar-item has-text-left ml-4 mb-2" to="/convert_units" @click="toggleActive">
					<fas-random class="icon" />
					<span class="ml-4"> Conversor de Unidades </span>
				</router-link>

				<router-link class="navbar-item has-text-left ml-4 mb-2" to="/convert_energy" @click="toggleActive">
					<fas-gas-pump class="icon" />
					<span class="ml-4"> Equivalente Energético </span>
				</router-link>

				<router-link class="navbar-item has-text-left ml-4 mb-2" to="/us" @click="toggleActive">
					<fas-user-friends class="icon" />
					<span class="ml-4"> Nosotros </span>
				</router-link>

				<router-link class="navbar-item has-text-left ml-4 mb-2" to="/tutorial" @click="toggleActive">
					<fas-question-circle class="icon" />
					<span class="ml-4"> Tutorial </span>
				</router-link>

				<a class="navbar-item has-text-left ml-4" @click="toggleModal">
					<fas-info-circle class="icon" />
					<span class="ml-4"> Acerca de </span>
				</a>
			</div>
		</div>
	</nav>
	<div class="over-ground" :class="[{ 'is-active': active }]"></div>
	<router-view></router-view>
	<div class="modal" :class="[{ 'is-active': active_modal }]">
		<div class="modal-background"></div>
		<div class="modal-card" ref="modal">
			<section class="modal-card-body">
				<button class="button is-white is-fullwidth is-justify-content-flex-end" @click="toggleModal">
					<fas-times />
				</button>
				<p class="modal-title is-size-3 mb-2">REDLAND</p>
				<p class="modal-title is-size-6">Energy Project Developers</p>
				<p class="is-size-5 mt-5">CONVERSOR DE UNIDADES 1.2</p>
				<p class="is-size-7">Copyriht &copy; 2021</p>
				<p class="is-size-7 mt-5">Gestor:</p>
				<p class="is-size-7">Ing. Edgard Ramirez Cadenillas</p>
				<p class="is-size-7 mt-5">Colaboradores:</p>
				<p class="is-size-7">Ing. Eduardo Torres Morales</p>
				<p class="is-size-7">Ing. Carlos Jiménez Huayta</p>
				<p class="is-size-7 mt-5">Desarrollador:</p>
				<p class="is-size-7">Xavier Méndez (xmmendezy@gmail.com)</p>
				<p class="is-size-7 mt-5">
					<a class="modal-title" @click="openURL">Sitio web Global REDLAND</a>
				</p>
			</section>
		</div>
	</div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { onClickOutside, templateRef } from '@vueuse/core';
import { useStorage } from '@vueuse/core';
import { Browser } from '@capacitor/browser';

const menu = templateRef('menu');
const modal = templateRef('modal');
const active = ref(false);
const active_modal = ref(false);
const transition = ref(false);

const toggleActive = () => {
	active.value = !active.value;
	if (!transition.value) {
		transition.value = true;
	}
};

const toggleModal = () => {
	active_modal.value = !active_modal.value;
	toggleActive();
};

const openURL = async () => {
	await Browser.open({ url: 'https://globalredland.com/' });
};

onClickOutside(menu, () => {
	if (active.value) {
		active.value = false;
	}
});

onClickOutside(modal, () => {
	if (active_modal.value) {
		active_modal.value = false;
	}
});

onMounted(() => {
	const tutorial = useStorage('tutorial', false);
	if (!tutorial.value) {
		const route = useRoute();
		if (route.path !== '/tutorial') {
			const router = useRouter();
			router.push('/tutorial');
		}
	}
});
</script>

<style lang="scss" scoped>
@import '../scss/vars.scss';

@keyframes navAnimIn {
	0% {
		left: -100%;
	}
	50% {
		left: -50%;
	}
	100% {
		left: 0;
	}
}

@keyframes navAnimOut {
	0% {
		left: 0;
	}
	50% {
		left: -50%;
	}
	100% {
		left: -100%;
	}
}

@keyframes overGroundIn {
	0% {
		background: rgba(0, 0, 0, 0);
	}
	50% {
		background: rgba(0, 0, 0, 0.2);
	}
	100% {
		background: $background-color;
	}
}

.navbar-brand {
	background-color: $primary;

	.navbar-burger {
		margin-left: unset;
		color: #fff;

		span {
			height: 2px;
		}
	}

	.navbar-title {
		width: 35%;
		margin: 0 auto;
		color: #fff;
	}
}

.navbar-menu {
	position: fixed;
	left: -100%;
	top: 3.25rem;
	width: 80vw;
	height: calc(100vh - 3.25rem);
	z-index: 10000000;
	display: block;

	&.menu-transition {
		animation: navAnimOut 200ms ease-in-out forwards;
	}

	&.is-active {
		left: 0;
		animation: navAnimIn 200ms ease-in-out forwards;
	}

	.navbar-start {
		.card {
			border-radius: 0;
			border-bottom: 1px solid #7c6e716e;
		}

		a.navbar-item:focus,
		a.navbar-item:focus-within,
		a.navbar-item:hover,
		a.navbar-item.is-active,
		.navbar-link:focus,
		.navbar-link:focus-within,
		.navbar-link:hover,
		.navbar-link.is-active {
			background-color: none;
			color: $primary;
		}
	}
}

.over-ground {
	position: absolute;
	height: 0;
	width: 0;

	&.is-active {
		height: 100vh;
		width: 100vw;
		animation: overGroundIn 200ms ease-in-out forwards;
	}
}

.modal {
	.modal-card {
		width: 90vw;

		.modal-card-head {
			background-color: #fff;
			border: none;
		}

		.modal-card-body {
			.modal-title {
				color: $primary;
			}
		}
	}
}
</style>
