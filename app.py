import sys,os
from EcoVision.pipeline.training_pipeline import TrainPipeline
from EcoVision.utils.main_utils import decodeImage, encodeImageIntoBase64
obj=TrainPipeline()
obj.run_pipeline()