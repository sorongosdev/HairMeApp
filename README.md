# 🗣️ 미용실 업무 보조 로봇, 테미

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

## 📜 7. 기능 설명

| 말하는 중 | 침묵 감지 | 텍스트 크기 변경 |
| :-----: | :-----: | :-----: |
| <img src="https://github.com/user-attachments/assets/589136e1-a920-4594-b114-f9f9cf4a627a" width="300"> | <img src="https://github.com/user-attachments/assets/bb23905b-4467-40cb-aa9c-ca0dae0dbb09" width="300"> | <img src="https://github.com/user-attachments/assets/c827bd03-289c-40a5-a216-1442fa5a15db" width="300"> |

| 저장 | 불러오기 |
| :-----: | :-----: |
| <img src="https://github.com/user-attachments/assets/d77cd833-f118-4a5c-8b09-c13a8c3d4499" width="300"> | <img src="https://github.com/user-attachments/assets/aa868273-41c0-4d66-bcc1-a2d706fdab9c" width="300"> |

## 💥 8. 트러블 슈팅

### ⚠️ 1) iOS 시뮬레이터 빌드 멈춤 문제
- Xcode에서 아래와 같은 에러 발생시,
  ```
  [FATAL:flutter/display_list/skia/dl_sk_dispatcher.cc(277)] Check failed: false.
  ```
- 프로젝트의 루트 경로에서 아래 명령어로 실행
  ```bash
  flutter run --no-enable-impeller
  ```

### ⚠️ 2) 맥 안드로이드 에뮬레이터에서 마이크 기능 미동작 문제
- 안드로이드 스튜디오에서 안드로이드 에뮬레이터를 실행하면 녹음 기능을 사용할 수 없음
- 터미널에서 호스트 오디오 권한을 주어 실행해야함
- iOS 시뮬레이터에서는 정상 동작함
- 해결 방법
  ① 터미널에서 emulator 관련 환경 변수 추가
  ```bash
  echo 'export PATH=$PATH:/Users/sora/Library/Android/sdk/emulator' >> ~/.zshrc
  
  source ~/.zshrc
  ```
  
  ② 에뮬레이터 리스트업
  ```bash
  emulator -list-avds
  ```
  
  ③ 오디오 권한을 허용하여 에뮬레이터 실행 (Pixel4_API34 부분에 에뮬레이터 이름)
     띄어쓰기 없이 이름 설정할 것. 안드로이드 스튜디오의 Device Manager에서 이름 변경 가능
  ```bash
  emulator -avd Pixel4_API34 -qemu -allow-host-audio
  ```
