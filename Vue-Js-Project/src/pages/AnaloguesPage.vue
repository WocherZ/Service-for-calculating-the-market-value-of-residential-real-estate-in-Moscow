<template>
  <q-page class="row">
    <div class="col-3 row justify-center content-center">
      <div class="text-h5 text-center q-pb-lg">
        Выберите квартиры-аналоги для расчета
      </div>
      <div class="col-12 row justify-center">
        <q-btn
          @click="calculatePool"
          flat
          bordered
          color="teal col-12 text-weight-light"
        >
          <q-icon left size="3em" name="calculate" />
          <span>Рассчитать пул</span>
        </q-btn>
      </div>
    </div>

    <div class="col-9 q-pr-md q-pl-md row content-center">
      <q-table
        class="col"
        title="Квартиры-аналоги"
        :rows="rows"
        :columns="columns"
        :row-key="createId"
        :selected-rows-label="getSelectedString"
        selection="multiple"
        v-model:selected="selected"
      />
    </div>
  </q-page>
</template>

<script>
import axios from "axios";
export default {
  name: "AnaloguesPage",
  data() {
    return {
      rows: [],
      fetched: null,
      columns: [
        {
          name: "location",
          align: "center",
          label: "Местоположение",
          field: "0",
          sortable: true,
        },
        {
          name: "rooms",
          align: "center",
          label: "Количество комнат",
          field: "1",
          sortable: true,
        },
        {
          name: "category",
          align: "center",
          label: "Сегмент",
          field: "2",
          sortable: true,
        },
        {
          name: "floors",
          align: "center",
          label: "Этажность дома",
          field: "3",
          sortable: true,
        },
        {
          name: "walls_material",
          align: "center",
          label: "Материал стен",
          field: "4",
          sortable: true,
        },
        {
          name: "floor",
          align: "center",
          label: "Этаж расположения",
          field: "5",
          sortable: true,
        },
        {
          name: "total_area",
          align: "center",
          label: "Площадь квартиры, кв.м",
          field: "6",
          sortable: true,
        },
        {
          name: "kitchen_area",
          align: "center",
          label: "Площадь кухни, кв.м",
          field: "7",
          sortable: true,
        },
        {
          name: "is_balcony",
          align: "center",
          label: "Наличие балкона/лоджии",
          field: "8",
          sortable: true,
        },
        {
          name: "metro_distance",
          align: "center",
          label: "Удаленность от станции метро, мин. пешком",
          field: "9",
          sortable: true,
        },
        {
          name: "condition",
          align: "center",
          label: "Состояние",
          field: "10",
          sortable: true,
        },
        {
          name: "price",
          align: "center",
          label: "Цена, млн. руб",
          field: "11",
          sortable: true,
        },
      ],
      selected: [],
      formattedSelected: [],
    };
  },
  methods: {
    createId(row) {
      return row.reduce((partialSum, a) => partialSum + a, 0);
    },

    getSelectedString() {
      return this.selected.length === 0
        ? ""
        : `${this.selected.length} record${
            this.selected.length > 1 ? "s" : ""
          } selected of ${this.rows.length}`;
    },
    formatSelected() {
      for (let row of this.selected) {
        console.log(row);
        let obj = {
          location: "",
          rooms: "",
          category: "",
          floors: "",
          walls_material: "",
          floor: "",
          total_area: "",
          kitchen_area: "",
          is_balcony: "",
          metro_distance: "",
          condition: "",
          price: "",
        };
        let i = 0;
        for (let key in obj) {
          let elem = row[i];
          obj[key] = elem + "";
          ++i;
        }
        this.formattedSelected.push(obj);
      }
    },
    calculatePool() {
      this.formatSelected();
      if (this.formattedSelected.length === 0) {
        this.$q.notify({
          type: "warning",
          message: "Ни одна квартира не выбрана",
        });
        return;
      }
      const url = "http://127.0.0.1:8081/api/analogs"; // квартиры-аналоги
      axios
        .post(url, this.formattedSelected)
        .then((response) => {
          this.$router.push("pool");
        })
        .catch((error) => {
          this.$q.notify({
            type: "negative",
            message: "Произошла ошибка",
          });
          console.log(error);
        });
    },
  },
  mounted() {
    let url = "http://127.0.0.1:8081/api/analogs"; // получение квартир-аналогов

    // under this url we would get the array of arrays

    axios
      .get(url)
      .then((response) => {
        this.rows = response.data;
        console.log(response);
      })
      .catch((error) => {
        this.$q.notify({
          type: "negative",
          message: "Произошла ошибка",
        });
        console.log(error);
      });
  },
};
</script>
