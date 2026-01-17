

const API_BASE = '';

function appGetToken() {
  return localStorage.getItem('jwt');
}

function appSetToken(token) {
  localStorage.setItem('jwt', token);
}

function appSetUsername(username) {
  localStorage.setItem('username', username);
}

function appGetUsername() {
  return localStorage.getItem('username') || '';
}

function appLogout() {
  localStorage.removeItem('jwt');
  localStorage.removeItem('username');
  localStorage.removeItem('role');
  appRefreshNavUser();
}

function appRefreshNavUser() {
  const el = document.getElementById('navUser');
  if (!el) return;
  const u = appGetUsername();
  el.textContent = u ? `Xin chào, ${u}` : 'Chưa đăng nhập';
}

async function apiFetch(path, opts = {}) {
  const headers = opts.headers || {};
  const token = appGetToken();
  if (token) headers['Authorization'] = 'Bearer ' + token;
  if (!headers['Content-Type'] && opts.body && !(opts.body instanceof FormData)) {
    headers['Content-Type'] = 'application/json';
  }

  const res = await fetch(API_BASE + path, { ...opts, headers });

  if (!res.ok) {
    const text = await res.text().catch(() => '');
    throw new Error(text || (`HTTP ${res.status}`));
  }
  const ct = res.headers.get('content-type') || '';
  if (ct.includes('application/json')) return await res.json();
  return await res.text();
}

function toast(msg, type = 'success') {
  const root = document.getElementById('toastRoot') || document.body;
  const div = document.createElement('div');
  div.className = `alert alert-${type} position-fixed top-0 end-0 m-3 shadow`;
  div.style.zIndex = 9999;
  div.textContent = msg;
  root.appendChild(div);
  setTimeout(() => div.remove(), 2600);
}

function qs(id) { return document.getElementById(id); }

function setLoading(btn, loading) {
  if (!btn) return;
  btn.disabled = loading;
  btn.dataset._oldText ||= btn.textContent;
  btn.textContent = loading ? 'Đang xử lý...' : btn.dataset._oldText;
}
