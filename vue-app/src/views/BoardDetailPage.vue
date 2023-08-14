
<template>
  <div>
    <div v-if="loading" class="loading-container">
      <i class="fa-solid fa-spinner"></i> 로딩 중...
    </div>
    <div v-else class="board-detail">
      <h2>{{ boardContent.title }}</h2>
      <div v-if="boardContent.fileDirs && boardContent.fileDirs.length > 0">
        <div v-for="(fileDir, index) in boardContent.fileDirs" :key="index">
          {{extractFileName(boardContent.fileDirs)}}
        </div>
      </div>
      <div>
        {{ boardContent.content }}
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      boardContent: {},
      loading: true,
    };
  },
  async created() {
    const boardId = this.$route.params.boardId;
    try {
      const response = await axios.get(`http://localhost:8080/board/view/${boardId}`);
      if (response.data.status.code === 200) {
        this.boardContent = response.data.content;
        this.loading = false;
      } else {
        console.error('올바르지 않은 요청입니다.');
        alert('올바르지 않은 요청입니다.');
      }
    } catch (error) {
      console.error('게시글 조회 오류:', error);
      alert('게시글 조회 오류');
    }
  },
  methods: {
    extractFileName(fileDir) {
      const parts = fileDir.split('_');
      if (parts.length > 1) {
        return parts[1]; // _ 뒤의 부분 반환
      }
      return fileDir; // _ 없으면 그대로 반환
    }
  }
};
</script>

<style>
.loading-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 200px;
  font-size: 18px;
}

.fa-spinner {
  animation: fa-spin 1s infinite linear;
}

@keyframes fa-spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.board-detail {
  margin: 20px;
}

.board-detail img {
  max-width: 100%;
  height: auto;
  margin: 10px 0;
}
</style>