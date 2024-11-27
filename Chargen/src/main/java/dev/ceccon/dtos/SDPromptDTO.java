package dev.ceccon.dtos;

public class SDPromptDTO {

    private String prompt;
    private String negative_prompt;
    private Long seed;
    private Integer steps;
    private Integer width;
    private Integer height;
    private Integer cfg_scale;
    private String sampler_name;
    private Integer n_iter;
    private Integer batch_size;

    public SDPromptDTO() { }

    public SDPromptDTO(String prompt, String negative_prompt, Long seed, Integer steps, Integer width, Integer height, Integer cfg_scale, String sampler_name, Integer n_iter, Integer batch_size) {
        this.prompt = prompt;
        this.negative_prompt = negative_prompt;
        this.seed = seed;
        this.steps = steps;
        this.width = width;
        this.height = height;
        this.cfg_scale = cfg_scale;
        this.sampler_name = sampler_name;
        this.n_iter = n_iter;
        this.batch_size = batch_size;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getNegative_prompt() {
        return negative_prompt;
    }

    public void setNegative_prompt(String negative_prompt) {
        this.negative_prompt = negative_prompt;
    }

    public Long getSeed() {
        return seed;
    }

    public void setSeed(Long seed) {
        this.seed = seed;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getCfg_scale() {
        return cfg_scale;
    }

    public void setCfg_scale(Integer cfg_scale) {
        this.cfg_scale = cfg_scale;
    }

    public String getSampler_name() {
        return sampler_name;
    }

    public void setSampler_name(String sampler_name) {
        this.sampler_name = sampler_name;
    }

    public Integer getN_iter() {
        return n_iter;
    }

    public void setN_iter(Integer n_iter) {
        this.n_iter = n_iter;
    }

    public Integer getBatch_size() {
        return batch_size;
    }

    public void setBatch_size(Integer batch_size) {
        this.batch_size = batch_size;
    }

}
