<template>
	<div>Tutorial</div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue';
import { useStorage } from '@vueuse/core';

onMounted(() => {
	const tutorial = useStorage('tutorial', false);
	if (!tutorial.value) {
		tutorial.value = true;
	}
});
</script>
