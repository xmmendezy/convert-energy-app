<template>
	<div class="button-nav is-flex is-flex-direction-row is-justify-content-space-around">
		<button
			v-if="typeof index === 'number' && typeof count === 'number' && index > 0 && index < count - 1"
			class="button is-white button-prev"
			@click="nav.prev"
		>
			<fas-chevron-left />
		</button>
		<router-link
			v-else-if="typeof index === 'number' && typeof count === 'number' && index < count - 1"
			to="/"
			class="button is-white button-prev"
		>
			Saltar
		</router-link>
		<button v-else class="button is-white button-prev"></button>
		<button
			v-if="typeof index === 'number' && typeof count === 'number' && index < count - 1"
			class="button is-white button-next"
			@click="nav.next"
		>
			<fas-chevron-right />
		</button>
		<router-link v-else to="/" class="button is-white button-next">Hecho</router-link>
	</div>
</template>

<script setup lang="ts">
import { inject } from 'vue';
import { CarouselNav } from 'vue3-carousel/dist/types/index';

defineProps({
	index: Number,
	count: Number,
});

const nav: CarouselNav = inject('nav', {});
</script>

<style lang="scss" scoped>
@import '../scss/vars.scss';

.button-nav {
	width: 100%;

	.button-prev,
	.button-next {
		color: $primary;
		background-color: transparent;
		border: none;
	}
}
</style>
