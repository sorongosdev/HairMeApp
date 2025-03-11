# 💇 HairMe - 미용실 서비스 로봇, 테미

## 📖 1. 프로젝트 소개

![테미 깃허브 표지](https://github.com/user-attachments/assets/fad0098f-1972-4365-8e66-1e62150370be)

- 모바일로봇 테미는 디스플레이가 탑재된 이동가능한 로봇입니다.
- 테미를 활용해 미용실 업무를 보조할 수 있는 시나리오를 구상하고, 로봇 전용 안드로이드 앱을 개발했습니다.

## 🛠️ 2. 개발 환경

### 🔍 1) 프레임워크 및 언어
- Front-end: Java 8 (1.8)
- Back-end: Real-time Firebase (29.3.1)

### 🔧 2) 개발 도구
- Android Studio: Bumblebee (2021.1.1)
- Gradle: 7.2
- JDK: 11

### 📱 3) 테스트 환경
- Android 에뮬레이터: API 레벨 32 (Android 12L)
- Mobile Robot Temi: API 레벨 32 (Android 12L)

### 📚 4) 주요 라이브러리 및 API
- robotemi:sdk: 0.10.77 (Temi 로봇 제어용 SDK)
- firebase-bom: 29.3.1 (Firebase 관련)
- firebase-analytics
- firebase-database

### 🔖 5) 버전 및 이슈 관리
- Git: 2.45.2

### 👥 6) 협업 툴
- 커뮤니케이션: Kakaotalk, Figma

### ☁️ 7) 서비스 배포 환경
- 백엔드 서버: Firebase Realtime Database
- 배포 방식: Firebase Cloud 호스팅

## ▶️ 3. 프로젝트 실행 방법

### ⬇️ 1) 필수 설치 사항

#### ① 기본 환경
- Android Studio (최신 버전)
- Java JDK (Java 8 이상)
- Android SDK (minSdk 23, targetSdk 32)
- Google Play 서비스 SDK

#### ② 필수 의존성 패키지
- androidx.appcompat:appcompat: 1.4.2
- com.google.android.material:material: 1.6.1
- androidx.constraintlayout:constraintlayout: 2.1.4

### ⿻ 2) 프로젝트 클론 및 설정
- 프로젝트 클론
```bash
git clone https://github.com/sorongosdev/HairTemi.git
```
- 의존성 설치
```bash
# Mac
./gradlew --refresh-dependencies

# Window
gradlew.bat --refresh-dependencies
```

### 🌐 3) 앱 빌드
```bash
./gradlew build
```

## 📁 4. 프로젝트 구조
```
example/soratemi3/
│  Agreement.java          # 이용 약관 동의 화면
│  CareInform.java         # 케어 서비스 정보 안내 화면
│  CareService.java        # 케어 서비스 선택 화면
│  CareStart.java          # 케어 서비스 시작 화면
│  CareStep1.java          # 케어 서비스 1단계 화면
│  CareStep2.java          # 케어 서비스 2단계 화면
│  CareStepf.java          # 케어 서비스 최종 단계 화면
│  Charge.java             # 요금 결제 화면
│  CheckHair.java          # 헤어 상태 체크 화면
│  DesignSel.java          # 헤어 디자인 선택 화면
│  FirebaseTest.java       # Firebase 연동 테스트 모듈
│  JuiceSel.java           # 음료 선택 화면
│  LoadingDrinkHere.java   # '이곳에서 음료' 로딩 화면
│  LoadingEmpty.java       # 빈 로딩 화면
│  LoadingFollow.java      # '따라오세요' 로딩 화면
│  MainActivity.java       # 앱 메인 화면
│  PayCheck.java           # 결제 확인 화면
│  PayReceipt.java         # 결제 영수증 화면
│  PayRfid.java            # RFID 결제 화면
│  Recommend.java          # 스타일 추천 화면
│  Register.java           # 고객 등록 화면
│
└─temi/                   # 테미 로봇 관련 기능
    CustomTtsListener.java     # 테미 로봇 음성 출력 리스너
    RoboTemi.java              # 테미 로봇 제어 클래스
    RoboTemiListeners.java     # 테미 로봇 이벤트 리스너
```

## 🎭 5. 역할

### 🐚 도소라

- 이용약관, 케어 서비스 정보, 음료 선택, RFID 결제 화면 등 전반적인 UI 개발
- 화면 전환 구현
- 화면 전환 시 가격 전달, 데이터 전달 구현

## 📅 6. 개발 기간
- 전체 개발 기간: 2022.03 ~ 2022.06
- 기획 및 디자인: 2022.03 ~ 2022.05
- 개발: 2022.05 ~ 2022.06

## 📜 7. 화면별 기능 설명

### 📄 1) 손님 입장

- 손님이 입장하면 웰컴드링크를 선택하도록 합니다.
- 회원카드를 RFID 센서에 태그해 대기 등록을 도와줍니다.
- 현재 대기 상황을 알려줍니다.
- 웰컴드링크가 준비되면, 빈 자리로 손님을 안내합니다.

| 웰컴드링크 선택 | 대기 등록 | 대기 안내 |
| :-----: | :-----: | :-----: |
| ![image](https://github.com/user-attachments/assets/2bb175b7-2615-4b0d-9f60-56052b90e5fa) | ![image](https://github.com/user-attachments/assets/12661d60-d29d-440b-abc2-a123efa5c4a1) | ![image](https://github.com/user-attachments/assets/36930b2b-f3ae-4944-930f-5ca2fa93dc99) |

| 웰컴드링크와 함께 빈자리 안내 전 알림 | 자리 안내 중 알림 |
| :-----: | :-----: |
| ![image](https://github.com/user-attachments/assets/35cbc623-6292-45dd-8441-ca1b988ac288) | ![image](https://github.com/user-attachments/assets/1fd7ea61-1c01-4ff8-9707-cd36fa0e08ba) |

### 📄 2) 시술 동의서

- 대기하는 손님이 시술에 동의한다는 시술 동의서를 작성할 수 있도록 합니다.
- 시술 시작을 알립니다.

| 시술 동의 | 시술 시작 안내 |
| :-----: | :-----: |
| ![image](https://github.com/user-attachments/assets/5618c14b-3ffe-47f5-9081-15c15b9cc710) | ![image](https://github.com/user-attachments/assets/7b701af5-2aa6-437f-946d-403cfb0dd794) |

### 📄 3) 시술 진행 정도 확인

- 시술 진행 정도를 보여주는 화면입니다.
- 헤어 디자이너는 진행 정도를 실시간으로 손님에게 테미를 통해 보여주어, 시술에 집중할 수 있습니다.

| 시술 진행 시작 | 시술 진행 1단계 |
| :-----: | :-----: | 
| ![image](https://github.com/user-attachments/assets/9c550404-f35e-4932-84ee-c1b3aae232b9) | ![image](https://github.com/user-attachments/assets/c4958d8b-e68f-4af5-8fb9-93a361ab3825) |

| 시술 진행 2단계 | 시술 완료 |
| :-----: | :-----: |
| ![image](https://github.com/user-attachments/assets/82bcf4e2-3da6-46cb-9cbf-df77d8b1f0da) | ![image](https://github.com/user-attachments/assets/d74f8bc7-04be-4e13-b6f0-36a71a8863f1) |

### 📄 4) 시술중 지원 서비스

- 뒤쪽에서 테미의 카메라로 실시간으로 두피 상태를 보여주는 서비스를 제공합니다.
- 원하는 헤어 시술만 직접 선택함으로써, 의도하지 않은 추가 서비스에 대한 요금이 발생하는 것을 미리 방지할 수 있습니다.

| 두피 상태 확인 | 원하는 머리 시술 선택 |
| :-----: | :-----: |
| ![image](https://github.com/user-attachments/assets/c894efc2-ffd7-4cc0-afe8-281d5c193ff8) | ![image](https://github.com/user-attachments/assets/275a03a3-16cc-499b-8fde-397d5e480c71) |

### 📄 5) 기타 서비스

- 테미를 통해 데스크의 직원을 호출할 수 있습니다.
- 원하는 헤어 제품을 직접 선택할 수 있어 부담 없이 고르고, 결제까지 가능합니다.
- 테미의 선반 위에 휴대폰을 올려두면 충전이 가능합니다.

| 도움 요청 | 상품 구매 | 휴대폰 충전 안내 |
| :-----: | :-----: | :-----: |
| ![image](https://github.com/user-attachments/assets/09998cca-31b9-41a5-b1d2-64b7a6eeb6df) | ![image](https://github.com/user-attachments/assets/be59393b-98b9-4eda-99f0-7426d15eb15e) | ![image](https://github.com/user-attachments/assets/45cd6925-cf37-411b-9ee6-ade8eb4ef423) |

### 📄 6) 결제

- 앞서 선택한 머리 시술과, 헤어 제품에 관한 결제를 확인할 수 있습니다.
- RFID 센서가 회원카드를 읽어 결제할 수 있도록 합니다.
- 남은 멤버십 포인트를 알려줍니다.

| 결제 확인 | 회원카드 결제창 | 남은 멤버십 포인트 안내 |
| :-----: | :-----: | :-----: |
| ![image](https://github.com/user-attachments/assets/c068c342-eca1-4346-bf61-198683bf849b) | ![image](https://github.com/user-attachments/assets/62cffd80-caf7-4a6c-aabe-4b252694a994) | ![image](https://github.com/user-attachments/assets/b3d23a2a-da98-491e-b673-0575f9ac0bc9) |
