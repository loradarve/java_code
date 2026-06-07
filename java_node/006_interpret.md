### 一、删去多余的conda环境，然后再删去对应名称的文件
```shell
    (base) C:\Users\48179>conda env list
    # conda environments:
    #
    base                  *  D:\anaconda3
    alien_invasion           D:\anaconda3\envs\alien_invasion
    bailing-main             D:\anaconda3\envs\bailing-main
    data_visualization       D:\anaconda3\envs\data_visualization
    fly                      D:\anaconda3\envs\fly
    jupyter_pytorch_cv       D:\anaconda3\envs\jupyter_pytorch_cv
    learning_log             D:\anaconda3\envs\learning_log
    m_learning               D:\anaconda3\envs\m_learning
    py_example               D:\anaconda3\envs\py_example
    yolotest                 D:\anaconda3\envs\yolotest


    (base) C:\Users\48179>conda remove -y -n fly --all
```
### 对于uv，删除文件等于删除环境，干净无残留