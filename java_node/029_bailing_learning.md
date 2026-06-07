
---

## Project Overview

**Bailing (百聆)** is an open-source voice assistant designed to engage in natural voice-based conversations. It combines **ASR** (Automatic Speech Recognition), **VAD** (Voice Activity Detection), **LLM** (Large Language Model), and **TTS** (Text-to-Speech) technologies to deliver GPT-4o-like voice dialogue with sub-800ms end-to-end latency. It is designed to run **without a GPU**, making it suitable for edge devices and low-resource environments.

**License:** MIT  
**Underlying LLM:** DeepSeek (via OpenAI-compatible API), also supports OpenAI, Qwen, Gemini, 01yi  
**Author:** wwbin2017 (Han Jiangxue / 寒江雪)

---

## Top-Level Directory Tree (3+ Levels Deep)

```
d:\py_example\bailing-main\
├── .gitignore
├── LICENSE                                            # MIT license
├── README.md                                          # Chinese main readme
├── README_en.md                                       # English readme
├── README_HELP.md                                     # User-run troubleshooting notes (Chinese)
├── main.py                                            # Main entry point
├── requirements.txt                                   # Python dependencies
│
├── .idea\                                             # PyCharm IDE project files
│   └── inspectionProfiles\
│       └── profiles_settings.xml
│
├── assets\                                            # Static assets
│   ├── bailing_flowchart.png
│   ├── bailing_flowchart_a.png
│   ├── example.png
│   └── logo.png
│
├── bailing\                                           # Core application package
│   ├── __init__.py                                    # Empty
│   ├── asr.py                                         # Speech Recognition (FunASR)
│   ├── dialogue.py                                    # Message & Dialogue classes
│   ├── llm.py                                         # LLM client (OpenAI/DeepSeek)
│   ├── memory.py                                      # Conversation memory manager
│   ├── player.py                                      # Audio playback (multiple backends)
│   ├── rag.py                                         # Retrieval-Augmented Generation
│   ├── recorder.py                                    # Microphone audio capture (PyAudio)
│   ├── robot.py                                       # Main Robot orchestrator
│   ├── tts.py                                         # Text-to-Speech (multiple backends)
│   ├── utils.py                                       # JSON/YAML helpers, text utilities
│   ├── vad.py                                         # Voice Activity Detection (SileroVAD)
│   └── __pycache__\                                   # Compiled bytecode cache
│       └── *.pyc
│
├── config\                                            # Configuration
│   └── config.yaml                                    # Main YAML configuration
│
├── documents\                                         # Local documents for RAG
│   └── README.md                                      # Lists supported formats: PDF, TXT, MarkDown, WORD
│
├── models\                                            # Pre-trained model files
│   ├── README.md                                      # (empty)
│   ├── SenseVoiceSmall\                               # ASR model (FunASR SenseVoice)
│   │   ├── README_zh.md
│   │   ├── am.mvn
│   │   ├── chn_jpn_yue_eng_ko_spectok.bpe.model
│   │   ├── config.yaml
│   │   ├── configuration.json
│   │   ├── demo.py
│   │   ├── model.pt
│   │   └── requirements.txt
│   └── bge-small-zh\                                  # Chinese embedding model (RAG)
│       ├── 1_Pooling\
│       │   └── config.json
│       ├── config.json
│       ├── config_sentence_transformers.json
│       ├── modules.json
│       ├── pytorch_model.bin
│       ├── sentence_bert_config.json
│       ├── special_tokens_map.json
│       ├── tokenizer.json
│       ├── tokenizer_config.json
│       └── vocab.txt
│
├── plugins\                                           # Plugin/function-call system
│   ├── __init__.py                                    # Empty
│   ├── README.md                                      # Plugin usage documentation (Chinese)
│   ├── function_calls_config.json                     # Function definitions for tool calling
│   ├── function_manager.py                            # Auto-import & function dispatch
│   ├── registry.py                                    # Function registry, Action, ActionResponse enums
│   ├── task_manager.py                                # TaskManager: async tool call orchestration
│   ├── functions\                                     # Individual plugin/function implementations
│   │   ├── __init__.py
│   │   ├── aigc_manus.py                              # General-purpose AI agent (OpenManus)
│   │   ├── get_day_of_week.py                         # Get current weekday/date
│   │   ├── get_weather.py                             # Weather lookup (moji.com)
│   │   ├── ielts_speaking_practice.py                 # IELTS speaking practice
│   │   ├── open_application.py                        # Open macOS applications
│   │   ├── schedule_task.py                           # Create timed reminders
│   │   ├── search_local_documents.py                  # Search local RAG documents
│   │   └── web_search.py                              # Web search
│   └── __pycache__\
│       └── *.pyc
│
├── server\                                            # Web-based dialogue viewer (Flask+SocketIO)
│   ├── server.py                                      # Flask backend
│   └── templates\
│       └── index.html                                 # Frontend HTML page
│
├── third_party\                                       # External projects
│   └── OpenManus\                                     # OpenManus AI agent framework
│       ├── .gitattributes
│       ├── .gitignore
│       ├── .pre-commit-config.yaml
│       ├── CODE_OF_CONDUCT.md
│       ├── LICENSE
│       ├── README.md / README_zh.md / README_ko.md / README_ja.md
│       ├── main.py
│       ├── requirements.txt
│       ├── run_flow.py
│       ├── setup.py
│       ├── assets\
│       │   └── community_group.jpg
│       ├── config\
│       │   ├── config.toml
│       │   └── config.example.toml
│       ├── examples\
│       │   └── readme.md
│       ├── logs\                                      # ~100 log files
│       │   └── 2025*.log, 2026*.log
│       └── app\
│           ├── __init__.py
│           ├── config.py / exceptions.py / llm.py / logger.py / schema.py
│           ├── agent\
│           │   ├── __init__.py / base.py / manus.py / planning.py / react.py / swe.py / toolcall.py
│           ├── flow\
│           │   ├── __init__.py / base.py / flow_factory.py / planning.py
│           ├── prompt\
│           │   ├── __init__.py / manus.py / planning.py / swe.py / toolcall.py
│           └── tool\
│               ├── __init__.py / base.py / bash.py / browser_use_tool.py
│               ├── create_chat_completion.py / file_saver.py / planning.py
│               ├── python_execute.py / run.py / str_replace_editor.py
│               ├── terminal.py / terminate.py / tool_collection.py / web_search.py
│               └── search\
│                   ├── __init__.py / baidu_search.py / base.py / bing_search.py
│                   ├── duckduckgo_search.py / google_search.py
│
└── tmp\                                               # Runtime temporary files (gitignored)
    ├── README.md                                      # Empty
    ├── bailing.log                                    # Application log
    ├── memory.json                                    # Persistent memory store
    ├── asr-*.wav                                      # ~180 recorded speech WAV files
    └── tts-*.wav / tts-*.wav.wav                      # ~80 synthesized speech WAV files
```


## 百聆 (Bailing) 项目技术总结

---

### 一、项目定位

百聆是一个**开源语音对话助手**，定位为 GPT-4o 级别的实时语音对话系统，核心卖点是**无需 GPU 即可运行**，适合边缘设备和低资源环境。端到端延迟控制在 **800ms 以内**。采用 MIT 开源协议。

---

### 二、整体架构：流水线式双工对话

整体架构遵循**流水线串联 + 双工全双工**的设计模式，数据流路径如下：

```
麦克风录音 → VAD语音检测 → ASR语音识别 → LLM大模型推理 → TTS语音合成 → 音频播放
   ↑                                                                    │
   └──────────────── 打断机制（interrupt）可在播放时中断 ──────────────┘
```

核心编排器是 [robot.py](file:///d:/py_example/bailing-main/bailing/robot.py) 中的 `Robot` 类，它负责：

1. **初始化六大模块**：Recorder、VAD、ASR、LLM、TTS、Player，全部通过 `config/config.yaml` 配置动态注入（工厂模式 + 抽象基类）。
2. **启动录音线程**（`start_recording`）：PyAudio 以 16kHz 单声道持续采集音频，写入线程安全的 `audio_queue`。
3. **启动 VAD 流式检测线程**（`_stream_vad`）：从 `audio_queue` 取音频帧，逐帧送入 SileroVAD 模型判断语音起止，结果写入 `vad_queue`。
4. **启动 TTS 优先级播放线程**（`_tts_priority`）：从 `tts_queue` 中按 FIFO 顺序取出 TTS 任务，调用对应播放器播放。
5. **主循环 `_duplex()`** 持续从 `vad_queue` 消费数据：
   - 检测到 **VAD start** → 开始缓存音频帧
   - 检测到 **VAD end** → 将缓存的音频帧送入 ASR 识别 → 识别文本送入 LLM
   - LLM 流式返回 token → 按标点分段切割 → 每个分段立即提交 TTS 任务 → 写入 `tts_queue`
6. **打断机制**：若用户说话时 LLM 正在播放语音，可中断当前播放（重置 `chat_lock`、清空播放器），实现真正的全双工对话体验。

---

### 三、ASR-LLM-TTS 各模块技术细节

#### 1. VAD（语音活动检测）

| 项目 | 详情 |
|------|------|
| **技术库** | SileroVAD（`silero_vad`） |
| **采样率** | 16kHz |
| **阈值** | 0.5 |
| **最小静音时长** | 400ms（可配置） |
| **推理方式** | 逐帧送 `VADIterator`，int16 → float32 归一化后传入 |

- 使用 SileroVAD 的 `VADIterator` 有状态迭代器，逐帧处理音频数据（每帧 512 采样点），返回 `start`/`end` 两种状态，分别代表说话开始和结束。
- 代码位于 [vad.py](file:///d:/py_example/bailing-main/bailing/vad.py)。

#### 2. ASR（语音识别）

| 项目 | 详情 |
|------|------|
| **技术栈** | 阿里巴巴 FunASR（`funasr==1.1.6`） |
| **模型** | SenseVoiceSmall（本地离线部署） |
| **推理框架** | PyTorch（`torch==2.6.0`） |
| **采样率** | 16kHz，单声道，16bit PCM |
| **语言** | 自动检测（支持中文、英文、粤语、日语、韩语） |

- 使用 FunASR 的 `AutoModel` 加载 SenseVoiceSmall 模型（文件位于 `models/SenseVoiceSmall/`），模型格式为 `.pt`（PyTorch JIT），无需 GPU。
- 识别时先将缓存的音频帧拼接保存为 WAV 文件，再送入模型 `generate()`，最后通过 `rich_transcription_postprocess` 做后处理（标点恢复、逆文本正则化等）。
- 同时支持语音识别 + 情感识别（SenseVoiceSmall 的特性）。
- 代码位于 [asr.py](file:///d:/py_example/bailing-main/bailing/asr.py)。

#### 3. LLM（大语言模型）

| 项目 | 详情 |
|------|------|
| **默认模型** | DeepSeek-V3（`deepseek-chat`） |
| **API协议** | OpenAI 兼容的 `/v1/chat/completions` |
| **SDK** | `openai==1.45.0`（Python 官方客户端） |
| **推理方式** | 流式输出（`stream=True`） |
| **Tool Call** | 支持 OpenAI 标准的 Function Calling |

- 代码位于 [llm.py](file:///d:/py_example/bailing-main/bailing/llm.py) 中的 `OpenAILLM` 类。
- 两个核心方法：
  - `response()`：标准流式对话，逐 token yield 返回内容。
  - `response_call()`：带工具调用的流式对话，同时 yield `(content, tool_calls)` 元组。
- 系统提示词中定义了角色为"百聆"，性格开朗活泼，要求回复简短、口语化、无 emoji。
- 对话管理由 [dialogue.py](file:///d:/py_example/bailing-main/bailing/dialogue.py) 的 `Dialogue` 类维护，支持多轮对话历史，自动持久化为 JSON 文件。
- **长记忆模块**（[memory.py](file:///d:/py_example/bailing-main/bailing/memory.py)）：读取历史对话 JSON，调用 LLM 生成摘要，存入 `tmp/memory.json`，在每次对话时注入系统提示词中。

#### 4. TTS（语音合成）

| 项目 | 详情 |
|------|------|
| **默认后端** | EdgeTTS（微软免费在线 TTS） |
| **可选后端** | 6 种：GTTS、MacTTS、EdgeTTS、ChatTTS、KOKOROTTS、CosyvoiceTTS |
| **语音** | `zh-CN-XiaoxiaoNeural`（微软晓晓神经语音） |

各后端对比：

| 后端 | 实现方式 | 特点 |
|------|---------|------|
| **EdgeTTS** | `edge_tts` 库，异步 HTTP 调用微软服务 | 免费、高质量、延迟低，默认中英文推荐方案 |
| **GTTS** | Google Text-to-Speech | 免费，需联网，质量一般 |
| **MacTTS** | `say` 命令行（macOS 内置） | 仅 macOS，离线 |
| **ChatTTS** | ChatTTS 本地模型 | 离线，支持语音克隆，需要 PyTorch |
| **KOKOROTTS** | Kokoro 管线（`kokoro>=0.7.4`） | 离线，多语言 |
| **CosyvoiceTTS** | 阿里 CosyVoice | 离线，高质量克隆 |

- 代码位于 [tts.py](file:///d:/py_example/bailing-main/bailing/tts.py)，统一抽象基类 `AbstractTTS`，工厂函数 `create_instance()` 动态实例化。

#### 5. Audio Player（音频播放）

| 项目 | 详情 |
|------|------|
| **默认后端** | PygameSoundPlayer |
| **可选后端** | 7 种：CmdPlayer、PyaudioPlayer、PygamePlayer、PygameSoundPlayer、SoundDevicePlayer、PydubPlayer、PlaysoundPlayer |

- 统一抽象基类 `AbstractPlayer`，支持 `play()` 播放、`stop()` 中断、`shutdown()` 清理。
- TTS 生成 WAV 音频文件后，Player 按优先级队列顺序播放。

---

### 四、Tool Call（工具调用/插件系统）

项目实现了一套完整的 Function Calling 插件体系：

| 文件 | 职责 |
|------|------|
| [plugins/registry.py](file:///d:/py_example/bailing-main/plugins/registry.py) | 装饰器 `@register_function()` 注册函数、定义 `Action` 枚举（NOTFOUND/NONE/RESPONSE/REQLLM/ADDSYSTEM/ADDSYSTEMSPEAK） |
| [plugins/task_manager.py](file:///d:/py_example/bailing-main/plugins/task_manager.py) | 从 `function_calls_config.json` 读取工具定义，自动导入插件模块，通过线程池异步调度工具调用 |
| [plugins/function_calls_config.json](file:///d:/py_example/bailing-main/plugins/function_calls_config.json) | 7 个 OpenAI 风格的工具定义 |

已内置的 8 个工具函数（位于 `plugins/functions/`）：

| 工具 | 功能 |
|------|------|
| `get_weather` | 查天气（墨迹天气） |
| `get_day_of_week` | 查当前日期/星期 |
| `ielts_speaking_practice` | 雅思口语练习 |
| `open_application` | 打开 macOS 应用 |
| `schedule_task` | 创建定时提醒 |
| `search_local_documents` | 本地 RAG 文档搜索 |
| `web_search` | 网页搜索 |
| `aigc_manus` | 通用 AI Agent（基于 OpenManus 框架） |

---

### 五、RAG（检索增强生成）

| 项目 | 详情 |
|------|------|
| **框架** | LangChain（`langchain==0.3.2`） |
| **向量数据库** | Chroma（`langchain_chroma`） |
| **嵌入模型** | BGE-small-zh（本地 `models/bge-small-zh/`） |
| **文档格式** | PDF、TXT、Markdown、Word |

- [rag.py](file:///d:/py_example/bailing-main/bailing/rag.py) 单例模式，初始化时加载 `documents/` 目录下的文档，构建 Chroma 向量索引。
- 用户通过 `search_local_documents` 工具触发检索，检索结果注入 LLM 上下文。

---

### 六、Web 服务（可选）

[server/](file:///d:/py_example/bailing-main/server/) 目录提供了基于 **Flask + Flask-SocketIO** 的 Web 对话查看器：
- 后端 `server.py` 提供 `/add_message` 端点接收对话消息。
- 前端 `templates/index.html` 实时显示对话流。
- `Robot` 类通过 `callback` 钩子将消息推送至 Web 端。

---

### 七、技术栈总结

| 层级 | 核心技术 | 依赖库 |
|------|---------|--------|
| **语音采集** | PyAudio 16kHz 单声道 PCM | `PyAudio==0.2.14` |
| **VAD** | SileroVAD 有状态迭代器 | `silero_vad==5.1`, `torch`, `numpy` |
| **ASR** | FunASR SenseVoiceSmall（本地离线） | `funasr==1.1.6`, `torch==2.6.0` |
| **LLM** | DeepSeek-V3（OpenAI 兼容 API） | `openai==1.45.0` |
| **LLM 对话管理** | 自研 Dialogue/Message 类 | 标准库 |
| **LLM 记忆** | 历史对话 → LLM 摘要 → 注入提示词 | 标准库 |
| **TTS** | EdgeTTS（默认）/ ChatTTS / GTTS / KOKOROTTS 等 6 种后端 | `edge_tts`, `ChatTTS`, `gTTS`, `kokoro` |
| **播放器** | Pygame / PyAudio / SoundDevice 等 7 种后端 | `pygame`, `pydub`, `sounddevice`, `soundfile` |
| **Tool Call** | 自研插件注册/调度系统 + 线程池 | `concurrent.futures` |
| **RAG** | LangChain + Chroma + BGE-small-zh | `langchain`, `langchain_chroma` |
| **Web** | Flask + Flask-SocketIO | `Flask`, `Flask_SocketIO` |
| **第三方 Agent** | OpenManus（AIGC通用Agent） | 自包含在 `third_party/OpenManus/` |
| **配置管理** | YAML 驱动动态模块注入 | `PyYAML` |

---

### 八、架构设计特点

1. **抽象基类 + 工厂模式**：所有模块（ASR/VAD/LLM/TTS/Player/Recorder）均定义抽象基类，通过 YAML 配置 + 反射动态实例化，切换后端零代码改动。
2. **多线程流水线**：录音线程、VAD 检测线程、TTS 优先级播放线程 + `ThreadPoolExecutor`（10 个 worker）实现并发，避免阻塞。
3. **流式首字延迟优化**：LLM 流式返回的 token 按标点实时分段，不等完整回复即可开始 TTS 合成和播放，显著降低端到端延迟。
4. **全双工打断**：在语音播放期间检测到用户开始说话，可立即中断播放、清空队列，重新进入聆听状态。
5. **无 GPU 依赖**：ASR 模型为 CPU 优化的 PyTorch JIT 模型，LLM 调用云端 API，整体可在无 GPU 的普通电脑/树莓派上运行。
6. **深度兼容 DeepSeek**：针对 DeepSeek 工具调用不太稳定的问题（经常把 JSON 输出到 content 而非 tool_calls 字段），代码中做了兼容处理（`extract_json_from_string` 从 content 中正则提取 JSON）。