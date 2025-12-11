const API_BASE_URL =
    import.meta.env.VITE_API_BASE_URL ||
    "https://efndpbyedj.us-west-2.awsapprunner.com";

async function handleResponse(res) {
    if (!res.ok) {
        let message = `HTTP ${res.status} ${res.statusText}`;

        try {
            const text = await res.text();
            // If backend returns useful error text, prefer that
            if (text) {
                message = text;
            }
        } catch {
            // ignore
        }

        throw new Error(message);
    }

    // Try to parse JSON, but fall back to text if needed
    const contentType = res.headers.get("content-type") || "";
    if (contentType.includes("application/json")) {
        return res.json();
    }
    const text = await res.text();
    try {
        return JSON.parse(text);
    } catch {
        return text;
    }
}

export const api = {
    async getHealth() {
        const res = await fetch(`${API_BASE_URL}/api/health`);
        return handleResponse(res);
    },
};