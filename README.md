# Ali AI App Generator 🎨🎬

**Ali AI App Generator** ایک جدید Android application ہے جو مصنوعی ذہانت (AI) کا استعمال کرتے ہوئے:

- ✅ **Text سے Image بنائے** (DALL-E 3)
- ✅ **Text سے Video بنائے** (RunwayML)
- ✅ **Image Edit کرے** (Delete, Crop, Filter)
- ✅ **Video Edit کرے** (Trim, Merge, Effects)
- ✅ **مختلف Levels** (Easy, Medium, Advanced, Professional)
- ✅ **High Quality Output**

## Features 🚀

### 1. Text-to-Image Generator
- OpenAI DALL-E 3 API کا استعمال
- High Resolution (1024x1024)
- Multiple Styles اور Themes
- Instant Download

### 2. Text-to-Video Generator
- RunwayML API Integration
- Video Quality: 720p, 1080p
- Custom Duration
- Background Music Support

### 3. Image Editor
- Crop, Resize, Rotate
- Filters اور Effects
- Brightness, Contrast, Saturation
- Object Detection اور Removal

### 4. Video Editor
- Trim اور Cut
- Merge Multiple Videos
- Add Text Overlays
- Sound Track Management

### 5. User Levels
- **Easy**: Simple, One-Click Features
- **Medium**: Advanced Controls
- **Advanced**: Professional Tools
- **Professional**: Custom API Parameters

## Technology Stack 🛠️

- **Language**: Java / Kotlin
- **Framework**: Android Studio
- **APIs**:
  - OpenAI DALL-E 3
  - RunwayML
  - Google Cloud Vision API
  - FFmpeg
- **Database**: Firebase Realtime Database
- **Storage**: Firebase Cloud Storage

## Installation 📱

```bash
git clone https://github.com/a47602245-create/ali-ai-app-generator-.git
cd ali-ai-app-generator-
```

## Requirements 📋

- Android 8.0 (API Level 26) یا بہتر
- 2GB RAM کم از کم
- Internet Connection
- API Keys:
  - OpenAI API Key
  - RunwayML API Key

## Configuration ⚙️

`.env` فائل میں اپنی API Keys add کریں:

```
OPENAI_API_KEY=your_openai_key
RUNWAYML_API_KEY=your_runway_key
GOOGLE_CLOUD_KEY=your_google_key
```

## Usage 🎯

1. App کھولیں
2. اپنی **Level** منتخب کریں
3. **Text** درج کریں
4. **Generate** بٹن دبائیں
5. Output **Download** کریں

## Project Structure 📁

```
ali-ai-app-generator/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/ali/
│   │   │   │   ├── activities/
│   │   │   │   ├── fragments/
│   │   │   │   ├── adapters/
│   │   │   │   ├── models/
│   │   │   │   ├── services/
│   │   │   │   ├── utils/
│   │   │   │   └── api/
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       ├── drawable/
│   │   │       └── values/
│   ├── build.gradle
│   └── AndroidManifest.xml
├── build.gradle
├── settings.gradle
└── README.md
```

## API Integration 🔌

### OpenAI DALL-E 3
```kotlin
val openAIClient = OpenAIClient(apiKey)
val image = openAIClient.generateImage(prompt, quality = "hd")
```

### RunwayML
```kotlin
val runwayClient = RunwayMLClient(apiKey)
val video = runwayClient.generateVideo(prompt, duration = 4)
```

## Contributing 👥

Pull requests خوش آمدید ہیں!

## License 📜

MIT License - دیکھیں `LICENSE` فائل

## Contact 📞

سوالات یا تجاویز کے لیے:
- GitHub Issues: https://github.com/a47602245-create/ali-ai-app-generator-/issues
- Email: support@aliapp.com

---

**Made with ❤️ by Ali AI Team**
