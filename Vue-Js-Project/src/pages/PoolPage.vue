<template>
  <q-page class="row">
    <!-- <input type="file" @change="onFileChange" /> -->

    <table id="my_table" style="display: none">
      <tr>
        <th>Местоположение</th>
        <th>Количество комнат</th>
        <th>Сегмент</th>
        <th>Этажность дома</th>
        <th>Материал стен</th>
        <th>Этаж расположения</th>
        <th>Площадь квартира</th>
        <th>Площадь кухни</th>
        <th>Наличие балкона/лоджии</th>
        <th>Удаленность от станции метро</th>
        <th>Состояние</th>
        <th>Цена, млн. руб</th>
      </tr>
      <tr v-for="row in rows" :key="row[0]">
        <td v-for="elem in row" :key="elem">
          {{ elem }}
        </td>
      </tr>
    </table>

    <div class="col-3 row justify-center content-center">
      <div class="col-12 row justify-center">
        <q-btn
          @click="exportToExcel"
          flat
          bordered
          color="teal col-12 text-weight-light"
        >
          <q-icon left size="3em" name="file_download" />
          <span>Экпорт таблицы</span>
        </q-btn>
      </div>
    </div>

    <div class="col-9 q-pr-md q-pl-md row content-center">
      <q-table
        class="col"
        title="Объекты оценки"
        :rows="rows"
        :columns="columns"
        :row-key="createId"
      />
    </div>

    <!-- <div class="q-mt-md col-12">Selected: {{ selected }}</div> -->
  </q-page>
</template>

<script>
import axios from "axios";
export default {
  name: "PoolPage",
  data() {
    return {
      rows: [],
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
    };
  },
  mounted() {
    let url = "http://127.0.0.1:8081/api/estimations";

    axios
      .get(url)
      .then((response) => {
        this.rows = response.data;
        console.log(response);
      })
      .catch((error) => {
        this.$q.notify({
          type: "negative",
          message: "Не удалось загрузить данные с сервера",
        });
        console.log(error);
      });
  },
  methods: {
    exportToExcel() {
      var wb = XLSX.utils.table_to_book(document.getElementById("my_table"));
      /* Export to file (start a download) */
      XLSX.writeFile(wb, "exported.xlsx");
    },
  },
};
</script>
