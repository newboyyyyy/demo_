<template>
  <div>
    <form @submit.prevent="submitForm" ref="form">
      <h2 class="mb-3">選擇員工</h2>
      <div class="mb-3">
        <label for="employeeSelect" class="form-label">選擇員工：</label>
        <select v-model="selectedEmployee" id="employeeSelect" class="form-select">
          <option v-for="employee in employees" :key="employee.emp_id" :value="employee.emp_id">
            {{ employee.name }} {{ employee.emp_id }}
          </option>
        </select>
      </div>

      <h2 class="mb-3">座位資訊</h2>
      <div style="width:170%">
        <span v-for="(seat, index) in seatingCharts" :key="seat.floor_seat_seq" :class="[
          'seat-container',
          { 'occupied-seat': seat.employee, 'selected-seat': index === selectedIndex, 'disabled-seat': seat.employee },
          { 'clickable-seat': !seat.employee && !seat.disabled },
        ]" :data-index="index + 1" @click="handleSeatClick(index)" :ref="`seat_${index}`">
          <span>{{ seat.floorNo }}樓: 座位{{ seat.seatNo }}</span>
          <span>{{ seat.employee ? ' [員編:' + seat.employee.emp_id + ']' : ' ' }}</span>
        </span>
      </div>
      <div class="mt-3">
        <button style="margin-left: 65%;" type="submit" class="btn btn-primary mb-3">送出</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      employees: [],
      selectedEmployee: null,
      seatingCharts: [],
      selectedIndex: null,
      seatRefs: [],
    };
  },
  mounted() {
    this.fetchEmployees();
    this.fetchSeatingCharts();
  },
  methods: {
    // 取得員工資訊
    fetchEmployees() {
      axios.get('http://localhost:8080/demo/emp/employees')
        .then(response => {
          this.employees = response.data;
        })
        .catch(error => {
          console.error('取得員工資訊時發生錯誤:', error);
        });
    },
    // 取得座位資訊
    fetchSeatingCharts() {
      axios.get('http://localhost:8080/demo/chart/seatingCharts')
        .then(response => {
          this.seatingCharts = response.data.map(seat => ({
            ...seat,
            disabled: seat.employee !== null,
          }));

          this.seatRefs = new Array(this.seatingCharts.length).fill(null);
        })
        .catch(error => {
          console.error('取得座位資訊時發生錯誤:', error);
        });
    },
    // 表單提交時的處理
    submitForm() {
      console.log("選擇的員工：", this.selectedEmployee);
      console.log("選擇的座位：", this.seatingCharts[this.selectedIndex]);

      if (this.selectedIndex !== null) {
        // 獲取綠色座位的 data-index
        const selectedSeatIndex = this.selectedIndex + 1;

        // 獲取選擇的員工
        const selectedEmployee = this.selectedEmployee;

        // 向後端發送數據
        axios.put(`http://localhost:8080/demo/chart/seatingCharts/${selectedSeatIndex}`, { selectedEmployee })
          .then(response => {
            console.log('成功發送數據到後端:', response.data);
            // 在這裡可以處理後端的響應
            this.$refs.form.reset();
            this.fetchEmployees();
            this.fetchSeatingCharts();
          })
          .catch(error => {
            console.error('發送數據到後端時發生錯誤:', error);
          });
      }
    },
    // 處理座位點擊事件
    handleSeatClick(index) {
      const clickedSeat = this.seatingCharts[index];

      if (clickedSeat.employee || clickedSeat.disabled) {
        return;
      }

      if (this.selectedIndex !== null && this.seatRefs[this.selectedIndex]) {
        this.seatRefs[this.selectedIndex].classList.remove('selected-seat');
      }

      this.selectedIndex = index;
      this.$set(this.seatRefs, index, this.$refs[`seat_${index}`]);
      this.seatRefs[index].classList.add('selected-seat');
    },
  },
};
</script>

<style>
/* ... 其他樣式 ... */
.seat-container {
  display: inline-block;
  width: 200px;
  box-sizing: border-box;
  padding: 10px;
  margin: 5px;
  border: 1px solid #ccc;
  cursor: pointer;
  transition: background-color 0.3s;
  /* 加入淡化效果的過渡動畫 */
}

/* 當座位被佔用時的樣式 */
.occupied-seat {
  background-color: crimson;
  color: white;
  pointer-events: none;
}

/* 當座位被選擇時的樣式 */
.selected-seat {
  background-color: green;
  color: white;
}

/* 可點擊的座位樣式 */
.clickable-seat {
  cursor: pointer;
}

/* 當座位被禁用時的樣式 */
.disabled-seat {
  cursor: not-allowed;
  pointer-events: none;
}

/* 滑鼠移上去時的樣式 */
.seat-container:hover {
  background-color: #e0e0e0;
  /* 這裡是淡淡的灰色，你可以根據需要調整 */
}
</style>
