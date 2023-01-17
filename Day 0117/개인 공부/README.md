# Front-End 개발 시작해보기 : React 편

# **React**

- Why ~ How
- CRA
- useState
- component
- DataGrid

# **Why?**

- 새로운 언어라는 막연함
- 눈에 안들어오는 코드의 난해함

# **How?**

- CRA(Create React App)
- babel
- webpack

# **설치 및 시작**

- Nodejs설치
    - Node Package Manager(NPM)
- CRA 설치
    - 설치 희망 path 이동
    - ex) C:\Users\vailish\test>npm create-react-app testpjt

# **React문법**

- `{ }`
- { title[0] }과 같이 써주면 인식함

JavaScript 

```
let title = ['이름', '전공']

// 중략
  <tr>
    <th scope="cols">{ title[0] }</th><th scope="cols">{ title[1] }</th>
  </tr>
// 중략
```

# **useState**

- 객체, 대체값
- 버튼 클릭시 요소가 바뀌게 들어가게 할 수 있음(아래 예시)

JavaScript 

```
import { useState } from 'react';
//중략

let [name, nameUpdate] = useState(['존스노우', '에드 스타크'])

// 중략
<button onClick={ () => { nameUpdate(['아리아', '산사'])}}></button>
// 중략
```

# **component**

- component 재사용

JavaScript 

```
// 생략
<tbody>
{
  name.map((n, i) => {
    return (
      <TrComp name={name[i]} major={major[i]}/>
    )
  })
}
</tbody>
// 중략

//component
fuction TrComp(props) {
  return (
    <tr>
      <th scope="row"> { props.name }></th>
      <td>{ props.major }</td>
    </tr>
    )
}

```

# **DataGrid**

JavaScript 

```
import { DataGrid } from '@mui/x-data-grid';
// 중략

const columns = [
  { field : 'id', headerName : '사번', width : 90 },
  { field : 'name', headerName : '이름', width : 90 },
  { field : 'teamNo', headerName : '팀', width : 90 },
  ];

// 중략

return (
  <div className="App">
    <div style ={{ height: 500, width: '100%' }}>
      <DataGrid rows={rows} cloumns={columns}/>
    </div>
  </div>
);
```