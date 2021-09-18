<template>
  <form @submit.prevent>
    <label>Street name:</label>
    <input type="text" required v-model="street">
    <label>Street number:</label>
    <input type="number" required v-model="number">
    <label>Radius</label>
    <input type="number" required v-model="radius">
    <label>Search Identifier: {{ searchIdentifier }}</label>
    <br>
    <div class="submit">
      <button @click="getContentFromAdress">Get Points of Interest</button>
    </div>


    <div v-for="category in responseCategories" :key="category">
      <!-- Using value -->
      <b-button v-b-toggle="category" class="m-1"> {{ category }}</b-button>
      <!-- Element to collapse -->
      <b-collapse :id="category">
        <b-card>
          <div v-for="item in elementsOfCategory[category]" :key="item">
            <li>{{ item }}</li>
          </div>
        </b-card>
      </b-collapse>
    </div>


  </form>
</template>

<script>
import axios from "axios"
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


export default {
  data() {
    return {
      street: '',
      number: '',
      radius: '',
      responseCategories: [],
      searchIdentifier: 'Currently no active search',
      elementsOfCategory: {},
      dummy: {a: [1, 2, 3]}
    }
  },
  methods: {
    getContentFromAdress() {
      let url = 'https://eis.dke.univie.ac.at/micro-service-controller-rest/rest/msc/callMicroserviceForced?microserviceId=7717445d-958c-4957-ab73-05ff9b157740&operationId=byAdress'
      console.log(url)
      axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
      axios.post(url, {
        "adress": {"value": this.street},
        "housenumber": {"value": this.number},
        "radius": {"value": this.radius}
      }).then((response) => {
        console.log(response)
        this.responseCategories = response.data.data.result.resaultObject;
        this.searchIdentifier = response.data.data.result.searchIdentifier;
      }).catch(error => {
        console.log("Error ========>", error);
      })
      this.checkCategoriesArrived();
    },
    checkCategoriesArrived() {
      if (this.responseCategories != 0) {
        this.getElementsOfCategory();
      } else {
        console.log("No information arrived from Olive yet")
        setTimeout(this.checkCategoriesArrived, 1000)
      }
    },
    getElementsOfCategory() {
      const urlCategory = "https://eis.dke.univie.ac.at/micro-service-controller-rest/rest/msc/callMicroserviceForced?microserviceId=7717445d-958c-4957-ab73-05ff9b157740&operationId=byCategory";
      const categories = this.responseCategories;
      //console.log(urlCategory)
      let i;
      for (i = 0; i < categories.length; i++) {
        let currentCategory = categories[i];
        axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
        axios.post(urlCategory, {
          "category": {"value": categories[i]},
          "searchIdentifier": {
            "value": this.searchIdentifier
          }
        }).then((response) => {
          //response.data.data.result.resaultObject.forEach(element => console.log(currentCategory + ":" + element.name + ", " + element.adress.writenAdress));
          let categoryValuesContent = [];
          //console.log(response)
          response.data.data.result.resaultObject.forEach(element => categoryValuesContent.push(element.name + ": " + element.adress.writenAdress));
          //Ne objekt mund te ruash cte duash. CurrentCategory eshte key dhe value eshte array me adresat dhe emrat
          this.$set(this.elementsOfCategory, currentCategory, categoryValuesContent);
        }).catch(error => {
          console.log("Error ========>", error);
        })
      }
      console.log(this.elementsOfCategory);
    }
  }
}
</script>

<style>
form {
  max-width: 420px;
  margin: 30px auto;
  background: white;
  text-align: left;
  padding: 10px;
  border-radius: 10px;
}

label {
  color: #aaa;
  display: inline-block;
  margin: 25px 0 15px;
  font-size: 0.6em;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bold;
}

input {
  display: block;
  padding: 10px 6px;
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid #ddd;
  color: #555;
}

textarea {
  width: 100%;
  resize: vertical;
}

input[type="checkbox"] {
  display: inline-block;
  width: 16px;
  margin: 0 10px 0 0;
  position: relative;
  top: 2px;
}

button {
  background: slategrey;
  border: 0;
  padding: 10px 20px;
  margin-top: 20px;
  color: white;
  border-radius: 20px;
}

.submit {
  text-align: center;
}
</style>