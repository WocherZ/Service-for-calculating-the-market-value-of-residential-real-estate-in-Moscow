<template>
  <q-page class="index text-weight-light text-white row text-h4 content-center">
    <div class="row col-7 justify-center">
      <div class="row justify-center">
        <div class="col-12 text-center">Аналитический центр</div>
        <div class="text-h6 text-italic col-12 text-center">
          Делаем вашу жизнь проще
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
export default {
  name: "IndexPage",
  data() {
    return {};
  },
};
</script>

<style>
.index {
  border: 1px solid black;
  background: no-repeat url(~assets/cropped_index_1.svg);
  background-size: 100%;
  background-position: center;
  background-size: cover;
}

.index_content {
  width: 80vw;
  border: 10px solid white;
}
</style>
