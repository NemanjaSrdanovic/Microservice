
<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <title>Please input an address and and a radius to find points of interest.</title>
    <SearchInput></SearchInput>
  </div>
</template>

<script>
import SearchInput from "@/components/SearchInput";

export default {
  name: 'App',

  components: {
    SearchInput
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
body{
  margin: 0;
  background: #eee;
}
img{
  width: 300px;
  height: auto;
}
title{
  color: #aaa;
  display: block;
  margin: 25px 0 5px;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bold;
}
</style>
