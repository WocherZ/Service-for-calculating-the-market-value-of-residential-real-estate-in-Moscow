<!-- <template>
  <q-layout>
    <q-header>
      <div
        class="row items-center text-h3 text-weight-light text-white text-center"
      >
        <div class="col-8 row justify-start items-center">
          <q-btn
            class="q-pl-xl q-pr-xl text-h3 text-weight-light"
            flat
            no-caps
            @click="toMain"
          >
            Главная
          </q-btn>

          <q-btn
            class="q-pl-xl text-h3 text-weight-light"
            flat
            no-caps
            @click="toCorrections"
          >
            Коррекции
          </q-btn>
        </div>

        <div class="col-4 justify-end row items-center">
          <q-btn to="/" flat>
            <q-icon left size="3em" name="logout" />
            <div>log out</div>
          </q-btn>

          <div class="text-weight-light text-h6 q-pr-md">
            <span><q-icon size="2em" name="person" /></span>
            <span> {{ $route.params.login }}</span>
          </div>
        </div>
      </div>
    </q-header>
    <q-page-container>
      <router-view></router-view>
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: "UserPage",
  components: {},
  data() {
    return {};
  },
  methods: {
    toMain() {
      this.$router.push(`/user/${this.$route.params.login}/`);
    },
    toCorrections() {
      this.$router.push(`/user/${this.$route.params.login}/corrections`);
    },
  },
  mounted() {
    this.toMain();
  },
};
</script>

<style></style> -->
