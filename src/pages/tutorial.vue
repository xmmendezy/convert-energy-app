<template>
	<Carousel>
		<slide v-for="(slide, i) in components" :key="i">
			<component :is="slide"></component>
		</slide>

		<template #addons="{ currentSlide, slidesCount }">
			<Navigation :index="currentSlide" :count="slidesCount" />
		</template>
	</Carousel>
</template>

<script setup lang="ts">
import { onMounted } from 'vue';
import { useStorage } from '@vueuse/core';
import { Carousel, Slide } from 'vue3-carousel';
import Tutorial1 from '../components/Tutorial1.vue';
import Tutorial2 from '../components/Tutorial2.vue';
import Tutorial3 from '../components/Tutorial3.vue';

const components = [Tutorial1, Tutorial2, Tutorial3];

onMounted(() => {
	const tutorial = useStorage('tutorial', false);
	if (!tutorial.value) {
		tutorial.value = true;
	}
});
</script>

<style lang="scss">
@import '~vue3-carousel/dist/carousel.css';

.carousel__item {
	min-height: 200px;
	width: 100%;
	background-color: var(--vc-clr-primary);
	color: var(--vc-clr-white);
	font-size: 20px;
	border-radius: 8px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.carousel__slide {
	padding: 0;
}
</style>

<route>
{
	meta: {
		layout: "tutorial"
	}
}
</route>
